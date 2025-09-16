#ifndef HTTP_MANAGER_H
#define HTTP_MANAGER_H

#include <HWCDC.h>
#include <HardwareSerial.h>
#include <HTTPClient.h>

// HTTP管理类
class HttpManager {
    private:
      String updateUrl;
      String loadUrl;
      int id;
    
    public:
      HttpManager(int id, String updateUrl, String loadUrl);
      String get();
      String post(String body);
      String post();
  };

//连接wifi的函数
void wifiInit(const char *ssid, const char *password);
#endif