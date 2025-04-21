#include "Light.h"

Light::Light(int id, String location, String status, int brightness, String Auto) {
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

void Light::setLocation(String location) { 
    this->location = location;
    Serial.println("位置更新: " + location);
}
void Light::setStatus(String status) { 
    this->status = status;
    Serial.println("状态更新: " + status);
}
void Light::setBrightness(int brightness) { 
    this->brightness = brightness;
    Serial.println("亮度更新: " + String(brightness));
}
void Light::setAuto(String Auto) { 
    this->Auto = Auto;
    Serial.println("自动模式更新: " + Auto);
}
void Light::setHumi(float humi) { 
    this->humi = humi;
    Serial.println("湿度更新: " + String(humi));
}
void Light::setTemp(float temp) { 
    this->temp = temp;
    Serial.println("温度更新: " + String(temp));
}
void Light::setLightIntensity(float lightIntensity) { 
    this->lightIntensity = lightIntensity;
    Serial.println("光照强度更新: " + String(lightIntensity));
}

int Light::getId() { return this->id; }
String Light::getLoaction() { return this->location; }
String Light::getStatus() { return this->status; }
int Light::getBrightness() { return this->brightness; }
String Light::getAuto() { return this->Auto; }
float Light::getLightIntensity() { return this->lightIntensity; }

String Light::getJsonString() {
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