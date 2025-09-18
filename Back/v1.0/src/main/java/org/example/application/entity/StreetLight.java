package org.example.application.entity;

import java.sql.Date;

public class StreetLight {
    //路灯id
    public int _id;
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

    @Override
    public String toString() {
        return "StreetLight{" +
                "_id=" + _id +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", brightness=" + brightness +
                ", auto='" + auto + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
