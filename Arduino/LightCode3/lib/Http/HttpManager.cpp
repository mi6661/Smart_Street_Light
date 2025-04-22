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
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl);
    int httpCode = http.GET();
    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        data = http.getString();
      }
    } else {
      Serial.printf("[HTTP]GET请求失败，错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
}

String HttpManager::post(String body) {
    String data = "null";
    HTTPClient http;
    http.begin(this->updateUrl);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST(body);
    if (httpCode > 0) {
      data = http.getString();
    } else {
      Serial.printf("[HTTP]POST请求失败-错误: %s\n", http.errorToString(httpCode).c_str());
      Serial.printf("[HTTP]POST请求失败-请求体:");Serial.println(body);
    }
    http.end();
    return data;
  }

String HttpManager::post() {
    String data = "null";
    HTTPClient http;
    http.begin(this->loadUrl + "?id=" + this->id);
    http.addHeader("Content-Type", "application/json");
    int httpCode = http.POST("");
    if (httpCode > 0) {
      data = http.getString();
    } else {
      Serial.printf("[HTTP]POST请求失败-错误: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    return data;
}