package org.example.application.dao;

public class LightInfo {
    public int id;
    public String location;
    public String status;
    public int brightness;
    public String auto;
    @Override
    public String toString() {
        return "LightInfo{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", brightness=" + brightness +
                '}';
    }
}
