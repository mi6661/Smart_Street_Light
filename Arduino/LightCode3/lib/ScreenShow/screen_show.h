#include <U8g2lib.h>
#ifndef SCREEN_SHOW_H
#define SCREEN_SHOW_H

class Screen_Show
{
private:
    /**
     * 这里仅通过u8g2显示处理好的文字，并不会对u8g2进行清屏或设置属性
     */
    U8G2_SSD1306_128X64_NONAME_F_SW_I2C& u8g2;
    //需要显示在屏幕上的数据。
    char time[20];
    char temperature[8];
    char humidity[8];
    char wind_speed[8];
public:
    //构造函数，传入u8g2对象
    Screen_Show(U8G2_SSD1306_128X64_NONAME_F_SW_I2C& u8g2);
    //设置时间
    void setTime(String time);
    //设置温度
    void setTemperature(float* temperature);
    //设置湿度
    void setHumidity(float* humidity);
    //设置风速
    void setVindSpeed(int* wind_speed);
    //初始化
    void init();
    //屏幕显示
    void show();
};

#endif