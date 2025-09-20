package com.gwen.smartlight.dto.web;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class SensorDataOnDetailCard {
    //数据ID
    int id;
    //温度
    float temperature;
    //湿度
    float humidity;
    //风速
    float wind_speed;
    //pm2.5
    float pm25;
    //数据采集时间
    Timestamp create_time;
}
