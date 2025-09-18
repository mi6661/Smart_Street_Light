package org.example.application.dao;


/*
* 开发板上出数据
*
* */
public class LightLite {
    /*温度*/
    public float temp;
    /*湿度*/
    public float humi;
    /*亮度*/
    public float light_sensor;
    /*风速*/
    public float wind_speed;

    @Override
    public String toString() {
        return "LightLite{" +
                "temp=" + temp +
                ", humi=" + humi +
                ", light_sensor=" + light_sensor +
                ", wind_speed=" + wind_speed +
                '}';
    }
}
