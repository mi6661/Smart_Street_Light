package org.example.application.dao;

public class LightInfo {
    public int id;
    public String location;
    public int status;
    public int brightness;

    @Override
    public String toString() {
        return "LightInfo{" +
                "location='" + location + '\'' +
                ", status=" + status +
                ", brightness=" + brightness +
                '}';
    }
}
