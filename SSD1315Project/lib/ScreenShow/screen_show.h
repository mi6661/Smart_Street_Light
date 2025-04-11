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
    //需要显示在屏幕上的数据。后续通过类函数进行处理
    char *message;
public:
    Screen_Show(U8G2_SSD1306_128X64_NONAME_F_SW_I2C& u8g2);
    void setMessage(char* message);
};

#endif