package com.gwen.smartlight.dto.web;


import lombok.Data;

@Data
public class SensorsTempNow {
    /*路灯ID*/
    int id;
    /*路灯最新温度数据*/
    float temperature;
}
