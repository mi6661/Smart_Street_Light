#ifndef GPIO_MANAGER_H
#define GPIO_MANAGER_H
#include <DHT.h>
class GpioManager
{
    private:
        /* data */
        int dht_pin;
        int light_sensor_analog_pin;        //光敏传感器AO引脚
        int light_sensor_digit_pin;         //光敏传感器DO引脚
        int led_pin;                        //路灯状态引脚
        DHT dht;
    public:
        GpioManager(int dht_pin,int light_sensor_analog_pin,int light_sensor_digit_pin,int led_pin);
        //初始化引脚
        void init();
        //获取光强
        int getLightValue();
        bool get_light_sensor();
        //获取温度
        float getTemperature();
        //获取湿度
        float getHumidity();
        //设置led电频
        void LedWrite(bool status);
};
#endif