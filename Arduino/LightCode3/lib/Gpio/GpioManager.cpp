#include "GpioManager.h"
#define DHTTYPE DHT11             // DHT 11

GpioManager::GpioManager(int dht_pin,int light_sensor_analog_pin,int light_sensor_digit_pin,int led_pin)
    :dht_pin(dht_pin),
    light_sensor_analog_pin(light_sensor_analog_pin),
    light_sensor_digit_pin(light_sensor_digit_pin),
    led_pin(led_pin),dht(dht_pin,DHTTYPE)
{
    init();
}

void GpioManager::init(){
    // 设置引脚模式
    pinMode(this->led_pin, OUTPUT);
    pinMode(this->light_sensor_analog_pin, INPUT);
    pinMode(this->light_sensor_digit_pin,INPUT);
    Serial.println("引脚初始化完成");
    Serial.println("系统初始化完成\n");
    // DHT11初始化和测试
    dht.begin();
    float testTemp = dht.readTemperature();
    float testHumi = dht.readHumidity();
    if (isnan(testTemp) || isnan(testHumi)) {
        Serial.println("DHT11传感器连接失败，请检查接线！");
    } else {
        Serial.println("DHT11传感器初始化成功");
        Serial.printf("初始读数 - 温度: %.2f°C, 湿度: %.2f%%\n", testTemp, testHumi);
    }
}

// 获取光照强度
int GpioManager::getLightValue() {
    int rawValue = analogRead(light_sensor_analog_pin);
    float voltage = rawValue / 4096.0*3.3;
    float resistance =  voltage/(3.3 - voltage)  * 10000.0;
    float X = 1000000.0 / resistance;
    float lightIntensity=40000*pow(X,-0.6021);
    if(lightIntensity>999)
    {
        lightIntensity=999;
    }
    //返回光照强度
    return lightIntensity;
}

//光敏传感(天黑为true，天亮为false)
bool GpioManager::get_light_sensor(){
    return digitalRead(this->light_sensor_digit_pin) == 0;
}

//获取温度
float GpioManager::getTemperature(){
    return this->dht.readTemperature();
}
//获取湿度
float GpioManager::getHumidity(){
    return this->dht.readHumidity();
}
//设置LED状态
void GpioManager::LedWrite(bool status){
    if(status){
        digitalWrite(this->led_pin,HIGH);
    }else{
        digitalWrite(this->led_pin,LOW);
    }
}