#include "JsonTool.h"
bool JsonTools::setJsonString(String jsonString) {
    this->jsonString = jsonString;
    this->error = deserializeJson(doc, jsonString);
    if (this->error) {
        Serial.print("JSON解析失败: ");
        Serial.println(error.f_str());
        Serial.print("解析失败的数据：");Serial.println(jsonString);
        return false;
    }
    return true;
}

String JsonTools::getLocation() {
    String location = this->doc["data"]["location"];
    return location;
}
String JsonTools::getLightStatus() {
    String status = this->doc["data"]["status"];
    return status;
}
int JsonTools::getBrightness() {
    int brightness = this->doc["data"]["brightness"];
    return brightness;
}
String JsonTools::getAuto() {
    String Auto = this->doc["data"]["auto"];
    return Auto;
}