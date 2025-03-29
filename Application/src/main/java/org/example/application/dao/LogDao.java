package org.example.application.dao;

import com.mysql.cj.log.Log;

public class LogDao {
    //用户id
    public int user_id;
    //路灯id
    public int light_id;
    //路灯状态操作(-1：off,1:on,0:auto)
    public int action;
    //路灯亮度(0-100)
    public int value;

    public LogDao(int user_id, int light_id, int action, int value) {
        this.user_id = user_id;
        this.light_id = light_id;
        this.action = action;
        this.value = value;
    }

    @Override
    public String toString() {
        return "LogDao{" +
                "user_id=" + user_id +
                ", light_id=" + light_id +
                ", action=" + action +
                ", value=" + value +
                '}';
    }
}
