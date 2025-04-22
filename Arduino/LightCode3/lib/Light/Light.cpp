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
}

void Light::setLocation(String location) { 
    this->location = location;
}
void Light::setStatus(String status) { 
    this->status = status;
}
void Light::setBrightness(int brightness) { 
    this->brightness = brightness;
}
void Light::setAuto(String Auto) { 
    this->Auto = Auto;
}
void Light::setHumi(float humi) { 
    this->humi = humi;
}
void Light::setTemp(float temp) { 
    this->temp = temp;
}
void Light::setLightIntensity(float lightIntensity) { 
    this->lightIntensity = lightIntensity;
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
    //Serial.println("生成的JSON数据: " + data);
    return data;
}