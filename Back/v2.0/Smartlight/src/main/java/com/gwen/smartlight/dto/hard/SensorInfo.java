package com.gwen.smartlight.dto.hard;

import lombok.Data;


/*
* 开发板上出数据模版类
* */
@Data
public class SensorInfo {
    /*路灯ID*/
    public int lightId;
    /*温度*/
    public float temp;
    /*湿度*/
    public float humi;
    /*光敏传感数据*/
    public float brightness;
    /*风速传感器数据*/
    public float wind_speed;

    public int getLightId() {
        return lightId;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumi() {
        return humi;
    }

    public void setHumi(float humi) {
        this.humi = humi;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }
}
