package org.example.application.entity;

import java.sql.Date;

public class ControlLog {
    //控制信息记录id
    public int _id;
    //用户id
    public int user_id;
    //路灯id
    public int light_id;
    //路灯状态操作（开：1 关：2 自动：0）
    public int action;
    //路灯亮度（0-100）
    public int value;
    //记录插入时间
    public Date create_time;


    @Override
    public String toString() {
        return "ControlLog{" +
                "_id=" + _id +
                ", user_id=" + user_id +
                ", light_id=" + light_id +
                ", action=" + action +
                ", value=" + value +
                ", create_time=" + create_time +
                '}';
    }
}
