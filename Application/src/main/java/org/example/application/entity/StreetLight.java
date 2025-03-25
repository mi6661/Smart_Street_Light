package org.example.application.entity;

import java.sql.Date;

public class StreetLight {
    //路灯id
    public int _id;
    //路灯位置
    public String location;
    //路灯状态(开：1，关：-1，自动：0)
    public int status;
    //路灯亮度（0-100）
    public int brightness;
    //创建时间
    public Date create_time;

    @Override
    public String toString() {
        return "StreetLight{" +
                "_id=" + _id +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", brightness=" + brightness +
                ", create_time=" + create_time +
                '}';
    }
}
