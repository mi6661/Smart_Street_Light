package org.example.application.entity;

import java.sql.Date;

//用户实体类

public class User {
    //用户id
    public int _id;
    //用户名
    public String username;
    //用户密码
    public String password;
    //用户权限（0-管理员；1-普通用户）
    public int role;
    //创建账号时间
    public Date create_time;


    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", create_time=" + create_time +
                '}';
    }
}
