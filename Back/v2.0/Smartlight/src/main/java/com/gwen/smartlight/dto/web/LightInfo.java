package com.gwen.smartlight.dto.web;

import lombok.Data;

@Data
public class LightInfo {
    /*路灯id*/
    int id;
    /*位置*/
    String location;
    /*开关状态*/
    String status;
    /*亮度*/
    int brightness;
    /*是否开启自动亮度*/
    String auto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }
}
