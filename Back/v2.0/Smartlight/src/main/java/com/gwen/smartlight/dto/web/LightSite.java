package com.gwen.smartlight.dto.web;


import lombok.Data;

/*
* 路灯位置信息
* */
@Data
public class LightSite {
    /*路灯ID*/
    int lightId;
    /*路灯经度*/
    String lat;
    /*路灯维度*/
    String lng;
}
