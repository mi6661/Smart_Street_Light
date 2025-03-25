package org.example.application.entity;

import java.sql.Date;

public class SensorData {
    //传感器数据id
    public int _id;
    //路灯id
    public int light_id;
    //温度
    public float temperature;
    //湿度
    public float humidity;
    //pm24浓度
    public float pm24;
    //数据插入时间
    public Date create_time;

    @Override
    public String toString() {
        return "SensorData{" +
                "_id=" + _id +
                ", light_id=" + light_id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pm24=" + pm24 +
                ", create_time=" + create_time +
                '}';
    }
}
