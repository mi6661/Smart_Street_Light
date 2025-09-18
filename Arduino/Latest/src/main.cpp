#include <HTTPClient.h>
#include <WiFi.h>
#include <DHT.h>
#include <ArduinoJson.h>
#include <string>

#define LIGHT_ID 1                          // 路灯编号
#define DHTPIN 4                            // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11                       // DHT 11
#define LIGHT_SENSOR_ANALOG_PIN 32          // 光敏传感器的 AO 引脚连接到 GPIO32
#define LIGHT_SENSOR_DIGIT_PIN 19           // 光敏传感器的 DO 引脚连接到 GPI19
#define LIGHT_PIN 5                           // LED 连接到 GPIO5
#define WIND_PIN 33                         // 风速传感器引脚 GPIO33
//基本信息配置
const char *ssid = "Gwen's Xiaomi 14";
const char *password = "asd620510";
String LOADURL = "http://192.168.129.82:8080/light/id";         //获取路灯信息url;
String UPDATEURL = "http://192.168.129.82:8080/light/board_update";  //更新路灯信息url;


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
    pinMode(LIGHT_PIN, OUTPUT);//路灯
    //pinMode(LIGHT_SENSOR_ANALOG_PIN, INPUT);//光敏——模拟输入
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
//获取光敏传感器状态
int getLightSensorD(){
  return digitalRead(LIGHT_SENSOR_DIGIT_PIN);
}
float getLightSensorA(){
    return analogRead(LIGHT_SENSOR_ANALOG_PIN);
}
//获取风速传感器值
float getWindSpeed(){
    return analogRead(WIND_PIN);
}
//设置路灯状态
void LedWrite(bool status){
    if(status){
        digitalWrite(LIGHT_PIN,LOW);
    }else{
        digitalWrite(LIGHT_PIN,HIGH);
    }
}

String httpget() {
    String data = "null";
    HTTPClient http;
    http.begin(LOADURL+"?id="+LIGHT_ID);
    int httpCode = http.GET();
    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        data = http.getString();
      }
    } else {
      Serial.printf("[HTTP]GET请求失败,错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
}

String httppost(String body) {
    String data = "null";
    HTTPClient http;
    http.begin(UPDATEURL);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST(body);
    if (httpCode > 0) {
      data = http.getString();
    } else {
      Serial.printf("[HTTP]POST请求失败-错误: %s\n", http.errorToString(httpCode).c_str());
      Serial.printf("[HTTP]POST请求失败-请求体:");Serial.println(body);
    }
    http.end();
    return data;
}

std::string createJsonString(float temp, float humi, float light_sensor,float wind_speed) {
    JsonDocument doc;

    doc["temp"] = temp;
    doc["humi"] = humi;
    doc["light_sensor"] = light_sensor;
    doc["wind_speed"] = wind_speed;

    std::string jsonString;
    serializeJson(doc, jsonString);
    return jsonString;
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
    //获取环境亮度
    float light_sensor = getLightSensorA();
    //获取风速
    float wind_speed = getWindSpeed();
    //展示数据
    std::string data = createJsonString(temp,humi,light_sensor,wind_speed);
    //Serial.printf("data:%s\n",data.c_str());
    //上传数据
    httppost(data.c_str());


    /*
    * 更新路灯状态
    */
    //TODO 这里需要根据继电器来设置高低平开关
    String status = httpget();
    //Serial.printf("%s\n",status);
    if (status.equals("on")){
        Serial.println("开灯");
        digitalWrite(LIGHT_PIN,HIGH);
    }else if(status.equals("off")){
        Serial.println("关灯");
        digitalWrite(LIGHT_PIN,LOW);
    }else{
        Serial.println("路灯请求错误");
    }
    
    delay(2000);
}
