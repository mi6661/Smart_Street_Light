package org.example.application.dao;


//用户登录dao实体
public class UserLogin {
    //用户名
    public String username;
    //用户密码
    public String password;
    //用户角色
    public int role;

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
