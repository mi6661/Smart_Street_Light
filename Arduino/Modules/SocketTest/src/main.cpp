#include <Arduino.h>
#include <WiFiClient.h>
#include <WiFi.h>

const char *ssid = "514";
const char *pass = "Abc12345678";

void setup(){
    //开启串口通信
    Serial.begin(9600);
    //连接WiFi
    WiFi.begin(ssid,pass);
    Serial.printf("开始连接WiFi:%s\n",ssid);
    while(!WiFi.isConnected()){
        Serial.print(".");
        delay(1000);
    }
    Serial.println("连接成功:");
    Serial.println(WiFi.localIP());
}

void loop(){

}
