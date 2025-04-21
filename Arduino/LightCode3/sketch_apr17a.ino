#include "DHT.h"
#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFi.h>
#include <math.h>

// light类
class Light {
private:
  int id;
  String location;
  String status;
  int brightness;
  String Auto;
  float humi;
  float temp;
  float lightIntensity;
  StaticJsonDocument<200> doc;

public:
  Light(int id, String location, String status, int brightness, String Auto) {
    this->id = id;
    this->location = location;
    this->status = status;
    this->brightness = brightness;
    this->Auto = Auto;
    this->humi = 0;
    this->temp = 0;
    this->lightIntensity = 0;
    Serial.println("Light对象初始化完成");
  }

  void setLocation(String location) { 
    this->location = location;
    Serial.println("位置更新: " + location);
  }
  void setStatus(String status) { 
    this->status = status;
    Serial.println("状态更新: " + status);
  }
  void setBrightness(int brightness) { 
    this->brightness = brightness;
    Serial.println("亮度更新: " + String(brightness));
  }
  void setAuto(String Auto) { 
    this->Auto = Auto;
    Serial.println("自动模式更新: " + Auto);
  }
  void setHumi(float humi) { 
    this->humi = humi;
    Serial.println("湿度更新: " + String(humi));
  }
  void setTemp(float temp) { 
    this->temp = temp;
    Serial.println("温度更新: " + String(temp));
  }
  void setLightIntensity(float lightIntensity) { 
    this->lightIntensity = lightIntensity;
    Serial.println("光照强度更新: " + String(lightIntensity));
  }

  int getId() { return this->id; }
  String getLoaction() { return this->location; }
  String getStatus() { return this->status; }
  int getBrightness() { return this->brightness; }
  String getAuto() { return this->Auto; }
  float getLightIntensity() { return this->lightIntensity; }

  String getJsonString() {
    this->doc["id"] = id;
    this->doc["location"] = location;
    this->doc["status"] = status;
    this->doc["brightness"] = brightness;
    this->doc["temperature"] = temp;
    this->doc["humidity"] = humi;
    this->doc["auto"] = Auto;
    this->doc["lightIntensity"] = lightIntensity;
    String data;
    serializeJson(this->doc, data);
    Serial.println("生成的JSON数据: " + data);
    return data;
  }
};

// HTTP管理类
class HttpManager {
private:
  String updateUrl;
  String loadUrl;
  int id;

public:
  HttpManager(int id, String updateUrl, String loadUrl) {
    this->id = id;
    this->updateUrl = updateUrl;
    this->loadUrl = loadUrl;
    Serial.println("HTTP管理器初始化完成");
    Serial.println("更新URL: " + updateUrl);
    Serial.println("加载URL: " + loadUrl);
  }

  String get() {
    Serial.println("开始GET请求...");
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl);
    int httpCode = http.GET();
    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        data = http.getString();
        Serial.println("GET请求成功，返回数据: " + data);
      }
    } else {
      Serial.printf("[HTTP]GET请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }

  String post(String body) {
    Serial.println("开始POST请求...");
    Serial.println("发送数据: " + body);
    String data = "null";
    HTTPClient http;
    http.begin(this->updateUrl);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST(body);
    if (httpCode > 0) {
      data = http.getString();
      Serial.println("POST请求成功，返回数据: " + data);
    } else {
      Serial.printf("[HTTP]POST请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }

  String post() {
    Serial.println("开始带参数的POST请求...");
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl + "?id=" + this->id);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST("");
    if (httpCode > 0) {
      data = http.getString();
      Serial.println("POST请求成功，返回数据: " + data);
    } else {
      Serial.printf("[HTTP]POST请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }
};

// Json工具类
class JsonTools {
private:
  String jsonString;
  StaticJsonDocument<200> doc;
  DeserializationError error;

public:
  bool setJsonString(String jsonString) {
    Serial.println("开始解析JSON数据...");
    Serial.println("原始JSON: " + jsonString);
    this->jsonString = jsonString;
    this->error = deserializeJson(doc, jsonString);
    if (this->error) {
      Serial.print("JSON解析失败: ");
      Serial.println(error.f_str());
      return false;
    }
    Serial.println("JSON解析成功");
    return true;
  }

  String getLocation() {
    String location = this->doc["data"]["location"];
    Serial.println("获取位置: " + location);
    return location;
  }
  String getLightStatus() {
    String status = this->doc["data"]["status"];
    Serial.println("获取状态: " + status);
    return status;
  }
  int getBrightness() {
    int brightness = this->doc["data"]["brightness"];
    Serial.println("获取亮度: " + String(brightness));
    return brightness;
  }
  String getAuto() {
    String Auto = this->doc["data"]["auto"];
    Serial.println("获取自动模式: " + Auto);
    return Auto;
  }
};

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

#define DHTPIN 4       // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11  // DHT 11
#define LIGHT_SENSOR_PIN 34 // 光敏传感器的 AO 引脚连接到 GPIO17
#define LED_PIN 5            // LED 连接到 GPIO5
#define RELAY_PIN 25         // 继电器连接到 GPIO25

// 获取光照强度
int getLightValue() {
  Serial.println("\n开始读取光照强度...");
  int rawValue = analogRead(LIGHT_SENSOR_PIN);
  float voltage = rawValue / 4096.0*3.3;
  float resistance =  voltage/(3.3 - voltage)  * 10000.0;
  float X = 1000000.0 / resistance;
  float lightIntensity=40000*pow(X,-0.6021);
  if(lightIntensity>999)
  {
    lightIntensity=999;
  }
  Serial.print("原始值: ");
  Serial.print(rawValue);
  Serial.print(", 电压: ");
  Serial.print(voltage);
  Serial.print("V, 电阻: ");
  Serial.print(resistance);
  Serial.print("Ω, 光照强度: ");
  Serial.println(lightIntensity);
  
  return lightIntensity;
}

//基本信息配置
Light light(1, "上海浦东新区", "off", 34, "on");
const char *ssid = "CMCC-022";
const char *password = "12345678";
String loadUrl = "http://49.232.141.65:8081/light/id";         //获取路灯信息url;
String updataUrl = "http://49.232.141.65:8081/light/updates";  //更新路灯信息url;
HttpManager manager(light.getId(), updataUrl, loadUrl);
JsonTools tools;
DHT dht(DHTPIN, DHTTYPE);  // 创建 DHT 传感器对象

void setup() {
  Serial.begin(115200);  // 设置更高的波特率
  Serial.println("\n系统初始化开始...");
  
  wifiInit(ssid, password);
  
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

  // 设置引脚模式
  pinMode(LED_PIN, OUTPUT);
  pinMode(LIGHT_SENSOR_PIN, INPUT);
  pinMode(RELAY_PIN, OUTPUT);  // 设置继电器引脚为输出模式
  digitalWrite(RELAY_PIN, HIGH);  // 初始化继电器为关闭状态
  Serial.println("引脚初始化完成");
  
  Serial.println("系统初始化完成\n");
}

void loop() {
  Serial.println("\n开始新的循环...");
  
  //从服务器同步数据
  Serial.println("从服务器获取数据...");
  String jsonData = manager.post();
  tools.setJsonString(jsonData);
  
  String status = tools.getLightStatus();
  int brightness = tools.getBrightness();
  String Auto = tools.getAuto();
  
  Serial.printf("当前状态 - 状态:%s, 亮度:%d, 自动模式:%s\n", status, brightness, Auto);
  
  light.setStatus(status);
  light.setBrightness(brightness);
  light.setAuto(Auto);

  // 读取光照强度
  float lightValue = getLightValue();
  light.setLightIntensity(lightValue);
  
  // 根据自动模式和光照强度控制继电器
  /*if (Auto == "on") {
    // 自动模式：根据光照强度控制
    if (lightValue < 100) {  // 光照强度阈值，可以根据需要调整
      digitalWrite(RELAY_PIN, LOW);  // 继电器低电平触发，打开LED
      Serial.println("自动模式：光照不足，继电器已开启");
    } else {
      digitalWrite(RELAY_PIN, HIGH);  // 继电器高电平关闭，关闭LED
      Serial.println("自动模式：光照充足，继电器已关闭");
    }
  } else {
    // 手动模式：根据后端状态控制
   
  }*/
  Serial.printf("-------------------%s",status);
   if (status == "on") {
      analogWrite(LED_PIN, 255);  // 继电器低电平触发
      Serial.println("手动模式：继电器已开启，LED已打开");

    } else {
      analogWrite(LED_PIN,0 );  // 继电器高电平关闭
      Serial.println("手动模式：继电器已关闭，LED已关闭");
    }

  // 读取温湿度数据
  Serial.println("\n读取温湿度数据...");
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

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

  // 发送数据到服务器
  Serial.println("\n准备发送数据到服务器...");
  String lightJson = light.getJsonString();
  String response = manager.post(lightJson);
  Serial.println("服务器响应: " + response);

  Serial.println("循环结束，等待10秒后继续...\n");
  delay(3000);
}