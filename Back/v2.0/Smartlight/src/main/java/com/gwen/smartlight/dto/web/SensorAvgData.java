package com.gwen.smartlight.dto.web;


import lombok.Data;

import java.sql.Date;

/*
* 每天平均温度，湿度，风速
* */

@Data
public class SensorAvgData {
    Date day;
    Float temp;
    Float humi;
    Float windspeed;
}
