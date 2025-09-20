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
}
