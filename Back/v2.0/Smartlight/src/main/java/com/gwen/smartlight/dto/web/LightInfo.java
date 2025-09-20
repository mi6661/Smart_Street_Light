package com.gwen.smartlight.dto.web;

import lombok.Data;

@Data
public class LightInfo {
    /*路灯id*/
    int id;
    /*位置*/
    String location;
    /*开关状态*/
    String status;
    /*亮度*/
    int brightness;
    /*是否开启自动亮度*/
    String auto;
}
