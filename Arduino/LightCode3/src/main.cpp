#include "DHT.h"
#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFi.h>
#include <math.h>
#include "Light.h"
#include "HttpManager.h"
#include "JsonTool.h"
#include "GpioManager.h"


#define DHTPIN 4                            // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11                       // DHT 11
#define LIGHT_SENSOR_ANALOG_PIN 32          // 光敏传感器的 AO 引脚连接到 GPIO17
#define LIGHT_SENSOR_DIGIT_PIN 19           // 光敏传感器的 DO 引脚连接到 GPI18
#define LED_PIN 5                           // LED 连接到 GPIO5



//基本信息配置
Light light(1, "上海浦东新区", "off", 34, "on");
const char *ssid = "CMCC-022";
const char *password = "12345678";
String loadUrl = "http://49.232.141.65:8081/light/id";         //获取路灯信息url;
String updataUrl = "http://49.232.141.65:8081/light/updates";  //更新路灯信息url;

//对象实例
HttpManager http(light.getId(), updataUrl, loadUrl);
JsonTools tools;
GpioManager gpio(DHTPIN,LIGHT_SENSOR_ANALOG_PIN,LIGHT_SENSOR_DIGIT_PIN,LED_PIN);
//日志输入对象，将其他线程的日志集中输出
char logs[1024*10] = "";

/**
 * 多线程
 * 1、路灯状态更新线程
 * 2、传感器数据更新线程
 * 3、数据同步线程
 * 4、数据上传线程
 */

 //light线程:
/**
  * 参数：null
  * 功能：1.设置路灯状态
  */
void light_task(void* args){
    Serial.println("路灯状态线程启动成功！");
    while(true){
        String status = light.getStatus();
        String Auto = light.getAuto();
        bool isblack= gpio.get_light_sensor();      // 路灯获取光敏传感器状态
        if(Auto == "on"){
            gpio.LedWrite(isblack);                 // 用户设置自动开关，路灯通过光敏传感器开关路灯
        }
        if(status == "on" && Auto == "off") {
            gpio.LedWrite(true);                    // 继电器低电平触发
        }
        if(status == "off" && Auto =="off"){
            gpio.LedWrite(false);                   // 继电器高电平关闭
        }
        delay(1000);
    }
}
//sensor线程
/**
 * 功能：1.更新传感器数据
 */
void sensor_task(void* args){
    Serial.println("传感器线程启动成功！");
    while(true){
        // 读取传感器数据
        float lightValue = gpio.getLightValue();
        int lightbool = gpio.get_light_sensor();
        float temperature = gpio.getTemperature();
        float humidity = gpio.getHumidity();

        // 保存传感器数据
        light.setLightIntensity(lightValue);
        if(!isnan(humidity))    light.setHumi(humidity);
        if(!isnan(temperature)) light.setTemp(temperature);
        
        // 输出日志
        Serial.printf("光照强度：AO-%.2f DO-%d, 温度: %.2f°C, 湿度: %.2f%%\n",lightValue,lightbool,temperature,humidity);
        delay(1000);
    }
}
//load_task
/**
 * 功能：1、同步服务器数据；
 */
void load_task(void* args){
    Serial.println("数据同步线程启动成功！");
    while(true){
        //从服务器同步数据
        String jsonData = http.post();
        //解析获取到的数据
        tools.setJsonString(jsonData);
        
        String status = tools.getLightStatus();
        int brightness = tools.getBrightness();
        String Auto = tools.getAuto();

        light.setStatus(status);
        light.setBrightness(brightness);
        light.setAuto(Auto);

        //日志输出
        Serial.printf("当前状态 - 状态:%s, 亮度:%d, 自动模式:%s\n", status, brightness, Auto);
        delay(2000);
    }
}
//数据上传线程
/**
 * 功能：1、上传本地数据
 * 
 * log：这里有一个错误，以为很严重，以及一直都没有注意到，所以写一个log来记录一下。
 * 以为使用了多线程，所以我么子上传本地数据时，路灯的status也被上传到服务器了。这里
 * 可能会造成硬件和前端抢夺路灯状态的控制权。解决办法有如下几种方法：
 *      1.硬件照常上传本地固定的状态数据，但是在后端将上传的status数据不做处理
 *      2.修改硬件的代码，不上传路灯状态status这个数据，但是这意味着我么又要
 *        创建个dao,这会破话硬件数据类light的唯一，我觉得硬件就一个数据对象
 *        就可以了（这是个人观点！！！）；
 *      3.把上传数据的线程和同步数据的线程数据合并（降低了数据的实效性，不推荐）
 */
void update_task(void* args){
    Serial.println("数据上传线程启动成功！");
    while(true){
        // 发送数据到服务器
        String lightJson = light.getJsonString();
        String response = http.post(lightJson);
        // 日志输出
        Serial.println("服务器响应: " + response);
        delay(3000);
    }
}

//连接wifi的函数
void wifiInit(const char *ssid, const char *password) {
    Serial.println("\n开始WiFi连接...");
    Serial.println("SSID: " + String(ssid));
    WiFi.begin(ssid, password);
    Serial.println("正在连接");
    while (WiFi.status() != WL_CONNECTED) {
        Serial.print(".");
        delay(1000);
    }
    Serial.println("\nWiFi连接成功");
    Serial.print("IP地址: ");
    Serial.println(WiFi.localIP());
}

void setup() {
    //串口初始化
    Serial.begin(115200);
    Serial.println("\n系统初始化开始...");
    //wifi初始化
    wifiInit(ssid, password);
    //线程启动
    xTaskCreate(light_task,"lightThread",1024*4,NULL,1,NULL);
    xTaskCreate(sensor_task,"sensorThread",1024*4,NULL,1,NULL);
    xTaskCreate(load_task,"loadThread",1024*10,NULL,1,NULL);
    xTaskCreate(update_task,"updateThread",1024*4,NULL,1,NULL);
}

void loop() {
    //在主循环中进行串口通行、和日输出
    delay(2000);
}