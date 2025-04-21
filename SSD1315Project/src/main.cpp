#include <Wire.h>
#include <U8g2lib.h>
#include <HardwareSerial.h>
#include <time.h>
#include <sys/time.h>
#include "at_utils.h"

HardwareSerial ml307Serial(2);
AtUtils atutils(ml307Serial);
char message[1024] = "hello world";

//校准esp32系统时间
void setTimeFromTimestamp(time_t timestamp) {
  struct timeval tv;//这个是esp32系统时间结构体对象
  tv.tv_sec = timestamp;
  tv.tv_usec = 0;
  settimeofday(&tv, NULL);  // 设置系统时间
}
//用于从系统获取当前时间，并返回时间的指定格式
String getTime() {
  struct tm timeinfo;
  if (!getLocalTime(&timeinfo)) {
    Serial.println("获取本地时间失败");
    return "Error";
  }

  String timet = "time:";
  timet.concat(timeinfo.tm_year + 1900);
  timet.concat('-');
  timet.concat(timeinfo.tm_mon + 1);
  timet.concat('-');
  timet.concat(timeinfo.tm_mday);
  timet.concat('-');
  timet.concat(timeinfo.tm_hour);
  timet.concat('-');
  timet.concat(timeinfo.tm_min);
  timet.concat('-');
  timet.concat(timeinfo.tm_sec);
  
  return timet;
}

//屏幕显示线程
void screen_task(void* param){   
  // 使用软件 I2C，指定引脚（SCL, SDA）
  U8G2_SSD1306_128X64_NONAME_F_SW_I2C u8g2(U8G2_R0, 
                                        /* clock=*/ 27,
                                        /* data=*/ 26,
                                        /* reset=*/ U8X8_PIN_NONE);
  String str;
  u8g2.setFont(u8g2_font_ncenB08_tr);   // 设置字体
  u8g2.begin();
  while (true){
    str = getTime();
    str.toCharArray(message,1024);
    u8g2.clearBuffer();                   // 清除缓冲区
    u8g2.drawStr(0,10,message);          // 显示文字
    u8g2.sendBuffer();                    // 推送到显示屏
    delay(500);
  }
}
//ML307R串口数据读取线性
  /**
   * 读取4G模块串口信息应该放在一个专门的线程中执行，这样便于分许读取的信息
   */
void ml307r_read_task(void* param){
  while(true){
    if(ml307Serial.available()){
      Serial.write(ml307Serial.read());
    }
    vTaskDelay(100);
  }
}


void setup() {
  //初始化4G模块串口通信&开发板串口
  ml307Serial.begin(9600,SERIAL_8N1,16,17);
  Serial.begin(9600);
  // 假设你通过串口/网络接收到这个时间戳（如：来自服务器）
  time_t timestamp = 1712808000;  // 示例：2024-04-11 10:00:00 UTC
  setTimeFromTimestamp(timestamp);  // 设置系统时间
  //启动屏幕显示线程
  xTaskCreate(screen_task,"screenThread",1024*4,NULL,1,NULL);
  //启动4G模块串口信息读取线程
  xTaskCreate(ml307r_read_task,"ml307r_read",1024*4,NULL,1,NULL);
}

void loop() {
  if(Serial.available()){
    char c = Serial.read();
    if(c=='1') atutils.at_test();
    if(c=='2') atutils.at_check();
    if(c=='3') atutils.at_addUrl("http://49.232.141.65:8081");
    if(c=='c') atutils.at_addUrl("https://cn.apihz.cn/api/time");
    if(c=='4') atutils.at_addHeader(0);
    if(c=='5') {
      atutils.at_http_get(0,"/user/list");
    }
  }
}

