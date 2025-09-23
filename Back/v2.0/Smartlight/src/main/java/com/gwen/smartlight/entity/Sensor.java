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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLight_id() {
        return light_id;
    }

    public void setLight_id(int light_id) {
        this.light_id = light_id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getPm25() {
        return pm25;
    }

    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}