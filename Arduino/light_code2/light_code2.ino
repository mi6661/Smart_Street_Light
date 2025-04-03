#include "DHT.h"
#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFi.h>  // Wi-Fi 库
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
  StaticJsonDocument<200> doc;

public:
  //构造方法
  Light(int id, String location, String status, int brightness, String Auto) {
    this->id = id;
    this->location = location;
    this->status = status;
    this->brightness = brightness;
    this->Auto = Auto;
    this->humi = 0;
    this->temp = 0;
  }
  // setter
  void setLocation(String location) {
    this->location = location;
  }
  void setStatus(String status) {
    this->status = status;
  }
  void setBrightness(int brightness) {
    this->brightness = brightness;
  }
  void setAuto(String Auto) {
    this->Auto = Auto;
  }
  void setHumi(float humi) {
    this->humi = humi;
  }
  void setTemp(float temp) {
    this->temp = temp;
  }
  // getter
  int getId() {
    return this->id;
  }
  String getLoaction() {
    return this->location;
  }
  String getStatus() {
    return this->status;
  }
  int getBrightness() {
    return this->brightness;
  }
  String getAuto() {
    return this->Auto;
  }
  //获取light的jsonString
  String getJsonString() {
    this->doc["id"] = id;
    this->doc["location"] = location;
    this->doc["status"] = status;
    this->doc["brightness"] = brightness;
    this->doc["temperature"] = temp;
    this->doc["humidity"] = humi;
    this->doc["auto"] = Auto;
    String data;
    serializeJson(this->doc, data);
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
  }
  // GET请求
  String get() {
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl);
    int httpCode = http.GET();
    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        data = http.getString();
      }
    } else {
      Serial.printf("[HTTP]GET... faild,error:%s\n",
                    http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }
  // POST_Body请求
  String post(String body) {
    String data = "null";
    HTTPClient http;
    http.begin(this->updateUrl);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST(body);
    if (httpCode > 0) {
      data = http.getString();
    } else {
      Serial.printf("[HTTP]POST... faild,error:%s\n",
                    http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }
  // POST_Param请求
  String post() {
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl + "?id=" + this->id);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST("");
    if (httpCode > 0) {
      data = http.getString();
    } else {
      Serial.printf("[HTTP]POST... faild,error:%s\n",
                    http.errorToString(httpCode).c_str());
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
    this->jsonString = jsonString;
    this->error = deserializeJson(doc, jsonString);
    //解析失败
    if (this->error) {
      Serial.print("JSON 解析失败: ");
      Serial.println(error.f_str());
      return false;
    }
    return true;  //放回设置是否成功
  }
  //下面这些方法只针对我的api
  //{"status":200,"message":"success","data":{"id":5,"location":"沈阳理工大学","status":"off","brightness":34,"auto":"on"}}
  String getLocation() {
    String location = this->doc["data"]["location"];
    return location;
  }
  String getLightStatus() {
    String status = this->doc["data"]["status"];
    return status;
  }
  int getBrightness() {
    int brightness = this->doc["data"]["brightness"];
    return brightness;
  }
  String getAuto() {
    String Auto = this->doc["data"]["auto"];
    return Auto;
  }
};

//连接wifi的函数
void wifiInit(const char *ssid, const char *password) {
  // wifi连接
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  Serial.println("开始连接");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.println("正在连接");
    delay(1000);
  }
  Serial.print("连接成功");
  Serial.print("IP:");
  Serial.println(WiFi.localIP());
}

#define DHTPIN 4       // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11  // DHT 11

// 定义光敏传感器引脚
#define LIGHT_SENSOR_PIN 17  // 光敏传感器的 AO 引脚连接到 GPIO17
#define LED_PIN 5            // LED 连接到 GPIO5

//基本信息配置
Light light(1, "上海浦东新区", "off", 34, "on");
const char *ssid = "Xiaomi  14";
const char *password = "asd620510";
String loadUrl = "http://192.168.1.120:8081/light/id";         //获取路灯信息url;
String updataUrl = "http://192.168.1.120:8081/light/updates";  //更新路灯信息url;
HttpManager manager(light.getId(), updataUrl, loadUrl);
JsonTools tools;
DHT dht(DHTPIN, DHTTYPE);  // 创建 DHT 传感器对象

void setup() {
  wifiInit(ssid, password);
  dht.begin();  // 初始化 DHT11 传感器

  // 设置 LED 引脚为输出
  pinMode(LED_PIN, OUTPUT);
  pinMode(LIGHT_SENSOR_PIN, INPUT);  // 设置光敏传感器引脚为输入
}

void loop() {
  //从服务器同步数据
  String jsonData = manager.post();
  Serial.println(jsonData);
  tools.setJsonString(jsonData);
  String status = tools.getLightStatus();
  int brightness = tools.getBrightness();
  String Auto = tools.getAuto();
  Serial.printf("status:%s  brightness:%d  Auto:%s\n", status, brightness, Auto);
  light.setStatus(status);
  light.setBrightness(brightness);
  light.setAuto(Auto);

  //设置灯的状态
  int value = 255 / 100 * brightness;
  if (status == "on") {
    analogWrite(LED_PIN, value);
  } else {
    analogWrite(LED_PIN, 0);
  }


  // 读取温湿度数据
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();
  light.setHumi(humidity);
  light.setTemp(temperature);
  String lightJson = light.getJsonString();
  Serial.println(lightJson);
  String stu = manager.post(lightJson);  //上传读取的传感器数据

  float lightValue = analogRead(LIGHT_SENSOR_PIN);
  Serial.print("光敏传感器值: ");
  Serial.println(lightValue);

  /*

    // 检查温湿度是否有效
    if (isnan(temperature) || isnan(humidity)) {
      Serial.println("读取温湿度数据失败");
    } else {
      // 打印温湿度数据到串口
      Serial.print("温度: ");
      Serial.print(temperature);
      Serial.print(" °C\t");

      Serial.print("湿度: ");
      Serial.print(humidity);
      Serial.println(" %");
    }

    // 读取光敏传感器数据（数字值）
    

    // 设置光照阈值来控制 LED
    if (lightValue == LOW) {  // 如果光线较弱，打开 LED
      digitalWrite(LED_PIN, HIGH);  // 打开 LED
    } else {
      digitalWrite(LED_PIN, LOW);   // 关闭 LED
    }
    */
  delay(3000);  // 每 3 秒读取一次数据
}