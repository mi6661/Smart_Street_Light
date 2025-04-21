#include <string>
#include <HardwareSerial.h>
#ifndef AT_UTILS_H
#define AT_UTILS_H

class AtUtils{
    private:
      /* data */
      HardwareSerial& hserial;
    public:
      //构造函数
      AtUtils(HardwareSerial& hserial);
      //测试4g模块是否启动
      void at_test();
      //查询模块信息
      void at_check();
      //添加api
      void at_addUrl(String url);
      //添加请求头
      void at_addHeader(int http_id);

      //get请求
      /**
       * param: 1.创建http时返回的id 2.请求后缀
       * return: void
       */
      void at_http_get(int http_id,String suffix); 

      //读取串口返回信息
      void at_http_response_handle(){
        
      }
  };
#endif