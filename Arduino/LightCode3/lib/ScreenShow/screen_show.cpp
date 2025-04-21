#include "screen_show.h"

Screen_Show::Screen_Show
(U8G2_SSD1306_128X64_NONAME_F_SW_I2C& u8g2):u8g2(u8g2){}

void Screen_Show::setTime(String time){
    time.toCharArray(this->time,32);
}
void Screen_Show::setTemperature(float* temp){
    char buffer[8];
    sprintf(buffer,"%.2f",*temp);
    strcpy(this->temperature,buffer);
}
void Screen_Show::setHumidity(float* humi){
    char buffer[8];
    sprintf(buffer,"%.2f",*humi);
    strcpy(this->humidity,buffer);
}
void Screen_Show::setVindSpeed(int* speed){
    char buffer[8];
    sprintf(buffer,"%d",*speed);
    strcpy(this->wind_speed,buffer);
}
void Screen_Show::init(){
    //设置显示字体
    u8g2.setFont(u8g2_font_ncenB08_tr);
    u8g2.begin();
}
void Screen_Show::show(){
    u8g2.clearBuffer();//清除缓存

    //这里以8像素为一行
    //第一行：时间
    u8g2.drawStr(0,8*1,this->time);          // 显示文字
    //第二行：温度
    u8g2.drawStr(0,8*2,this->temperature);          // 显示文字
    //第三行：湿度
    u8g2.drawStr(0,8*3,this->humidity);
    //第四行：风速
    u8g2.drawStr(0,8*4,this->wind_speed);

    u8g2.sendBuffer();//推送显示内容
}