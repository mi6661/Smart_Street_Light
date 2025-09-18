#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFi.h>
#include <math.h>
#include "Light.h"
#include "HttpManager.h"
#include "JsonTool.h"
#include "GpioManager.h"
#include <thread.h>


const char *ssid = "514";
const char *password = "Abc12345678";


void setup() {
    //串口初始化
    Serial.begin(115200);
    Serial.println("\n系统初始化开始...");
    //wifi初始化
    wifiInit(ssid, password);

    //线程启动
    xTaskCreate(light_task,"lightThread",1024*4,NULL,1,NULL);       //路灯
    xTaskCreate(sensor_task,"sensorThread",1024*4,NULL,1,NULL);     //传感器
    xTaskCreate(load_task,"loadThread",1024*10,NULL,1,NULL);        //更新状态
    xTaskCreate(update_task,"updateThread",1024*4,NULL,1,NULL);     //上传数据
}

void loop() {
    //在主循环中进行串口通行、和日输出
    delay(2000);
}