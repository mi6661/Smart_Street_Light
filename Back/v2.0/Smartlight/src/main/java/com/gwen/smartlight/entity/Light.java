package com.gwen.smartlight.entity;

import lombok.Data;

import java.sql.Date;


@Data
public class Light {
    //路灯id
    public int id;
    //地区
    public String district;
    //路灯位置
    public String location;
    //路灯状态
    public String status;
    //路灯亮度（0-100）
    public int brightness;
    //路灯自动状体
    public String auto;
    //创建时间
    public Date create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}