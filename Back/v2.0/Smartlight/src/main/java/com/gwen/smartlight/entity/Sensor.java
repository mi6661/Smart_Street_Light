package com.gwen.smartlight.entity;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Sensor {
    //传感器数据id
    public int id;
    //路灯id
    public int light_id;
    //温度
    public float temperature;
    //湿度
    public float humidity;
    //环境光数据
    public float brightness;
    //风速
    public float wind_speed;
    //pm2.5浓度
    public float pm25;
    //数据插入时间
    public Timestamp create_time;
}