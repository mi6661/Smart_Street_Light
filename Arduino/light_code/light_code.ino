#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

const char* ssid = "514";
const char* password = "Abc12345678";
JsonDocument json;

//连接wifi的函数
void wifiInit(const char* ssid, const char* password) {
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


//HTTP_GET请求
String http_get(String url) {

  String data = "off";
  //http请求
  HTTPClient http;                   //声明HTTPClient对象
  http.begin(url);                   //准备开始连接
  int httpCode = http.GET();         //发出GET请求
  if (httpCode > 0) {                //状态码大于0说明请求成功
    if (httpCode == HTTP_CODE_OK) {  //被服务器正常响应，等同于httpCode == 200
      data = http.getString();       //读取响应的数据
                                     //Serial.println(data);
    }
  } else {
    Serial.printf("[HTTP]GET... failed,error:%s\n", http.errorToString(httpCode).c_str());
  }
  http.end();
  return data;
}
//HTTP_POST



//解析json数据
void getJson(String data) {
  Serial.println(data);
  //创建json解析对象
  StaticJsonDocument<200> doc;
  //解析json
  DeserializationError error = deserializeJson(doc, data);
  //解析失败
  if (error) {
    Serial.print("JSON 解析失败: ");
    Serial.println(error.f_str());
    return;
  }
  //解析Json数组
  JsonArray array = doc.as<JsonArray>();

  Serial.println("解析的 JSON 数据：");
  for (JsonObject obj : array) {
    int id = obj["_id"];
    const char* location = obj["location"];
    const char* status = obj["status"];
    int brightness = obj["brightness"];
    const char* autoMode = obj["auto"];
    const char* createTime = obj["create_time"];

    // 输出解析结果
    Serial.println("--------------------------");
    Serial.print("ID: ");
    Serial.println(id);
    Serial.print("位置: ");
    Serial.println(location);
    Serial.print("状态: ");
    Serial.println(status);
    Serial.print("亮度: ");
    Serial.println(brightness);
    Serial.print("自动模式: ");
    Serial.println(autoMode);
    Serial.print("创建时间: ");
    Serial.println(createTime);
  }
}



void setup() {
  wifiInit(ssid, password);
  String data = http_get("http://192.168.3.41:8081/light/list");
  getJson(data);
}

void loop() {
}