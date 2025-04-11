#include "at_utils.h"

//构造函数
AtUtils::AtUtils(HardwareSerial& hserial):hserial(hserial){}
//测试4g模块是否启动
void AtUtils::at_test(){
    hserial.write("AT\r\n");
}
//查询模块信息
void AtUtils::at_check(){
    hserial.write("ATI\r\n");
}
//添加api
void AtUtils::at_addUrl(String url){
    String at = "AT+MHTTPCREATE=\""+url+"\"\r\n";
    char At[50];
    at.toCharArray(At,50);
    hserial.write(At);
}
//添加请求头
void AtUtils::at_addHeader(int http_id){
    String at = "AT+MHTTPCFG=\"header\",";
    at.concat(http_id);
    at.concat(",\"Content-Type: application/json\"\r\n");
    const char* At = at.c_str();
    //hserial.write("AT+MHTTPCFG=\"header\",0,\"Content-Type: application/json\"\r\n");
    hserial.write(At);
}
//get请求
void AtUtils::at_http_get(int http_id,String suffix){
    String at = "AT+MHTTPREQUEST=";
    at.concat(http_id);
    at.concat(",1,0,\"");
    at.concat(suffix);
    at.concat("\"\r\n");
    const char* At = at.c_str();
    hserial.write(At);
}