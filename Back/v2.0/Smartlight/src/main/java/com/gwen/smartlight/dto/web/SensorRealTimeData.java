package com.gwen.smartlight.dto.web;

import lombok.Data;

@Data
public class SensorRealTimeData {
    /*路灯ID*/
    int lightId;
    /*位置*/
    String location;
    /*温度*/
    float temperature;
    /*湿度*/
    float humidity;
    /*风速*/
    float windSpeed;
}
