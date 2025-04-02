package org.example.application.dao;

public class LightSensor {
    public int id;
    public String location;
    public String status;
    public int brightness;
    public float temperature;
    public float humidity;
    public String auto;


    @Override
    public String toString() {
        return "LightSensor{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", brightness=" + brightness +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", auto='" + auto + '\'' +
                '}';
    }
}
