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
#define LIGHT_SENSOR_ANALOG_PIN 18          // 光敏传感器的 AO 引脚连接到 GPIO17
#define LIGHT_SENSOR_DIGIT_PIN 19           // 光敏传感器的 DO 引脚连接到 GPI18
#define LED_PIN 5                           // LED 连接到 GPIO5



//基本信息配置
Light light(1, "上海浦东新区", "off", 34, "on");
const char *ssid = "Xiaomi  14";
const char *password = "asd620510";
String loadUrl = "http://49.232.141.65:8081/light/id";         //获取路灯信息url;
String updataUrl = "http://49.232.141.65:8081/light/updates";  //更新路灯信息url;

//对象实例
HttpManager http(light.getId(), updataUrl, loadUrl);
JsonTools tools;
GpioManager gpio(DHTPIN,LIGHT_SENSOR_ANALOG_PIN,LIGHT_SENSOR_DIGIT_PIN,LED_PIN);


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
    while(true){
        String status = light.getStatus();
        String Auto = light.getAuto();

        if(Auto=="on"){
            gpio.LedWrite(gpio.get_light_sensor());
            continue;
        }

        if (status == "on"&&Auto=="off") {
          gpio.LedWrite(true);  // 继电器低电平触发
          Serial.printf("[light_task]-手动模式:继电器已开启,LED已打开");
    
        }else{
          gpio.LedWrite(false);  // 继电器高电平关闭
          Serial.println("[light_task]手动模式:继电器已关闭,LED已关闭");
        }
        delay(1000);
    }
}
//sensor线程
/**
 * 功能：1.更新传感器数据
 */
void sensor_task(void* args){
    while(true){
        // 读取光照强度
        float lightValue = gpio.getLightValue();
        light.setLightIntensity(lightValue);
        // 读取温湿度数据
        Serial.println("\n读取温湿度数据...");
        float temperature = gpio.getTemperature();
        float humidity = gpio.getHumidity();

        // 检查是否读取失败
        if (isnan(temperature) || isnan(humidity)) {
            Serial.println("DHT11读取失败！");
        // 如果读取失败，不更新值，保持原来的值
        } else {
            // 只有在读取成功时才更新值
            light.setHumi(humidity);
            light.setTemp(temperature);
            Serial.printf("温度: %.2f°C, 湿度: %.2f%%\n", temperature, humidity);
        }
        delay(1000);
    }
}
//load_task
/**
 * 功能：1、同步服务器数据；
 */
void load_task(void* args){
    while(true){
        //从服务器同步数据
        Serial.println("从服务器获取数据...");
        String jsonData = http.post();
        tools.setJsonString(jsonData);

        String status = tools.getLightStatus();
        int brightness = tools.getBrightness();
        String Auto = tools.getAuto();
        light.setStatus(status);
        light.setBrightness(brightness);
        light.setAuto(Auto);
        Serial.printf("当前状态 - 状态:%s, 亮度:%d, 自动模式:%s\n", status, brightness, Auto);
        delay(2000);
    }
}
//数据上传线程
/**
 * 功能：1、上传本地数据
 */
void update_task(void* args){
    while(true){
        // 发送数据到服务器
        Serial.println("\n准备发送数据到服务器...");
        String lightJson = light.getJsonString();
        String response = http.post(lightJson);
        Serial.println("服务器响应: " + response);

        Serial.println("循环结束，等待3秒后继续...\n");
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
    //DHT模块测试
    float testTemp = gpio.getTemperature();
    float testHumi = gpio.getHumidity();
    if (isnan(testTemp) || isnan(testHumi)) {
        Serial.println("DHT11传感器连接失败，请检查接线！");
    }else{
        Serial.println("DHT11传感器初始化成功");
        Serial.printf("初始读数 - 温度: %.2f°C, 湿度: %.2f%%\n", testTemp, testHumi);
    }
    //线程启动
    xTaskCreate(light_task,"lightThread",1024*4,NULL,1,NULL);
    xTaskCreate(sensor_task,"sensorThread",1024*4,NULL,1,NULL);
    xTaskCreate(load_task,"loadThread",1024*10,NULL,1,NULL);
    xTaskCreate(update_task,"updateThread",1024*4,NULL,1,NULL);
}

void loop() {
    //在主循环中进行串口通行
    
    delay(2000);
}

