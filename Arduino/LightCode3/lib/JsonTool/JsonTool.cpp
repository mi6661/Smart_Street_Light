#include "JsonTool.h"
bool JsonTools::setJsonString(String jsonString) {
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

String JsonTools::getLocation() {
    String location = this->doc["data"]["location"];
    Serial.println("获取位置: " + location);
    return location;
}
String JsonTools::getLightStatus() {
    String status = this->doc["data"]["status"];
    Serial.println("获取状态: " + status);
    return status;
}
int JsonTools::getBrightness() {
    int brightness = this->doc["data"]["brightness"];
    Serial.println("获取亮度: " + String(brightness));
    return brightness;
}
String JsonTools::getAuto() {
    String Auto = this->doc["data"]["auto"];
    Serial.println("获取自动模式: " + Auto);
    return Auto;
}