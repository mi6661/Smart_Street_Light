#include <Arduino.h>
#include <utils.h>



//wifi 
const char *ssid = "514";
const char *password = "Abc12345678";
#define DHTPIN 4                            // DHT11 数据引脚连接到 GPIO 4
#define DHTTYPE DHT11                       // DHT 11
#define LIGHT_SENSOR_ANALOG_PIN 32          // 光敏传感器的 AO 引脚连接到 GPIO17
#define LIGHT_SENSOR_DIGIT_PIN 19           // 光敏传感器的 DO 引脚连接到 GPI18
#define LED_PIN 5                           // LED 连接到 GPIO5




void setup() {
    Serial.begin(115200);
    Serial.println("Starting!");
    wifiInit(ssid,password);
}

void loop() {
  // put your main code here, to run repeatedly:
}









