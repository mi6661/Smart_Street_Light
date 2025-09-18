#include "DHT.h"
#include <HTTPClient.h>
#include <WiFi.h>

#define DHTPIN 4                            // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11                       // DHT 11
#define LIGHT_SENSOR_ANALOG_PIN 18          // 光敏传感器的 AO 引脚连接到 GPIO17
#define LIGHT_SENSOR_DIGIT_PIN 19           // 光敏传感器的 DO 引脚连接到 GPI18
#define LED_PIN 5                           // LED 连接到 GPIO5

//基本信息配置
const char *ssid = "Xiaomi  14";
const char *password = "asd620510";
String LOADURL = "http://49.232.141.65:8081/light/id";         //获取路灯信息url;
String UPDATEURL = "http://49.232.141.65:8081/light/updates";  //更新路灯信息url;


//温湿传感器对象
DHT dht(DHTPIN,DHTTYPE);

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
//引脚初始化
void GPIOInit(){
  // 设置引脚模式
    pinMode(LED_PIN, OUTPUT);//路灯
    pinMode(LIGHT_SENSOR_ANALOG_PIN, INPUT);//光敏——模拟输入
    pinMode(LIGHT_SENSOR_DIGIT_PIN,INPUT);//光敏——数字输入
    Serial.println("引脚初始化完成");
    Serial.println("系统初始化完成\n");
    // DHT11初始化和测试
    dht.begin();
    float testTemp = dht.readTemperature();
    float testHumi = dht.readHumidity();
    if (isnan(testTemp) || isnan(testHumi)) {
        Serial.println("DHT11传感器连接失败，请检查接线！");
    } else {
        Serial.println("DHT11传感器初始化成功");
        Serial.printf("初始读数 - 温度: %.2f°C, 湿度: %.2f%%\n", testTemp, testHumi);
    }
}

//获取温度
float getTemperature(){
    return dht.readTemperature();
}
//获取湿度
float getHumidity(){
    return dht.readHumidity();
}
//获取路灯状态
int getLightStatus(){
    return digitalRead(LED_PIN);
}
//获取光敏传感器状态
int getLightSensor(){
  return digitalRead(LIGHT_SENSOR_DIGIT_PIN);
}
//设置LED状态
void LedWrite(bool status){
    if(status){
        digitalWrite(LED_PIN,LOW);
    }else{
        digitalWrite(LED_PIN,HIGH);
    }
}

void setup() {
      //串口初始化
    Serial.begin(115200);
    Serial.println("\n系统初始化开始...");
    //wifi初始化
    wifiInit(ssid, password);
    //I/O初始化
    GPIOInit();
}

void loop() {
    //读取温度
    float temp = getTemperature();
    //读取湿度
    float humi = getHumidity();
    //读取路灯状态
    int status = getLightStatus();


}
