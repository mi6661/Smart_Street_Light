#include <utils.h>


void wifiInit(const char *ssid, const char *password) {
    Serial.println("\n开始WiFi连接...");
    Serial.println("SSID: " + String(ssid));
    WiFi.begin(ssid, password);
    Serial.println("正在连接");
    while (WiFi.status() != WL_CONNECTED) {
        Serial.print(".");
        delay(1000);
    }
    Serial.println("\nWiFi连接成功");
    Serial.print("IP地址: ");
    Serial.println(WiFi.localIP());
}