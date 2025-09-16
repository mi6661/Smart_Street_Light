// In Light.cpp
#include "Light.h"

Light::Light(int id, String location, String status, int brightness, String Auto) {
    // Create the mutex when the object is constructed
    this->mutex = xSemaphoreCreateMutex();
    if (this->mutex == NULL) {
        // Handle error: mutex creation failed
        // You might want to print a message or handle it differently
        Serial.println("Error: Mutex creation failed!");
    }

    // Now, set the initial values. This is still single-threaded, so no need for a lock here.
    this->id = id;
    this->location = location;
    this->status = status;
    this->brightness = brightness;
    this->Auto = Auto;
    this->humi = 0;
    this->temp = 0;
    this->lightIntensity = 0;
}

// ------------------- Setters with Mutex Lock -------------------
void Light::setLocation(String location) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->location = location;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setStatus(String status) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->status = status;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setBrightness(int brightness) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->brightness = brightness;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setAuto(String Auto) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->Auto = Auto;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setHumi(float humi) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->humi = humi;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setTemp(float temp) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->temp = temp;
        xSemaphoreGive(this->mutex);
    }
}
void Light::setLightIntensity(float lightIntensity) { 
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->lightIntensity = lightIntensity;
        xSemaphoreGive(this->mutex);
    }
}

// ------------------- Getters with Mutex Lock -------------------
int Light::getId() { 
    int tempId = 0;
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempId = this->id;
        xSemaphoreGive(this->mutex);
    }
    return tempId;
}
String Light::getLoaction() { 
    String tempLocation = "";
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempLocation = this->location;
        xSemaphoreGive(this->mutex);
    }
    return tempLocation;
}
String Light::getStatus() { 
    String tempStatus = "";
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempStatus = this->status;
        xSemaphoreGive(this->mutex);
    }
    return tempStatus;
}
int Light::getBrightness() { 
    int tempBrightness = 0;
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempBrightness = this->brightness;
        xSemaphoreGive(this->mutex);
    }
    return tempBrightness;
}
String Light::getAuto() { 
    String tempAuto = "";
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempAuto = this->Auto;
        xSemaphoreGive(this->mutex);
    }
    return tempAuto;
}
float Light::getLightIntensity() { 
    float tempLightIntensity = 0.0;
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        tempLightIntensity = this->lightIntensity;
        xSemaphoreGive(this->mutex);
    }
    return tempLightIntensity;
}

// ------------------- getJsonString() with Mutex Lock -------------------
String Light::getJsonString() {
    String data;
    if(xSemaphoreTake(this->mutex, portMAX_DELAY) == pdTRUE) {
        this->doc["id"] = id;
        this->doc["location"] = location;
        this->doc["status"] = status;
        this->doc["brightness"] = brightness;
        this->doc["temperature"] = temp;
        this->doc["humidity"] = humi;
        this->doc["auto"] = Auto;
        this->doc["lightIntensity"] = lightIntensity;
        
        serializeJson(this->doc, data);
        
        xSemaphoreGive(this->mutex);
    }
    //Serial.println("生成的JSON数据: " + data);
    return data;
}