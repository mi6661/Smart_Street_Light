package org.example.application.dao;

public class SensorDao {
    //路灯id
    public int light_id;
    //温度
    public float temperature;
    //湿度
    public float humidity;
    //pm25浓度
    public float pm24;

    public SensorDao(int light_id, float temperature, float humidity, float pm24) {
        this.light_id = light_id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pm24 = pm24;
    }

    public SensorDao() {}

    @Override
    public String toString() {
        return "SensorDao{" +
                "light_id=" + light_id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pm24=" + pm24 +
                '}';
    }
}
