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
}