#include "HttpManager.h"
HttpManager::HttpManager(int id, String updateUrl, String loadUrl) {
    this->id = id;
    this->updateUrl = updateUrl;
    this->loadUrl = loadUrl;
    Serial.println("HTTP管理器初始化完成");
    Serial.println("更新URL: " + updateUrl);
    Serial.println("加载URL: " + loadUrl);
  }

String HttpManager::get() {
    Serial.println("开始GET请求...");
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl);
    int httpCode = http.GET();
    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        data = http.getString();
        Serial.println("GET请求成功，返回数据: " + data);
      }
    } else {
      Serial.printf("[HTTP]GET请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
}

String HttpManager::post(String body) {
    Serial.println("开始POST请求...");
    Serial.println("发送数据: " + body);
    String data = "null";
    HTTPClient http;
    http.begin(this->updateUrl);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST(body);
    if (httpCode > 0) {
      data = http.getString();
      Serial.println("POST请求成功，返回数据: " + data);
    } else {
      Serial.printf("[HTTP]POST请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
  }

String HttpManager::post() {
    Serial.println("开始带参数的POST请求...");
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl + "?id=" + this->id);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST("");
    if (httpCode > 0) {
      data = http.getString();
      Serial.println("POST请求成功，返回数据: " + data);
    } else {
      Serial.printf("[HTTP]POST请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
}