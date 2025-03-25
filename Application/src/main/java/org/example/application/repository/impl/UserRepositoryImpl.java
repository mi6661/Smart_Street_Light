package org.example.application.repository.impl;

import org.example.application.dao.UserLogin;
import org.example.application.entity.User;
import org.example.application.repository.UserRepository;
import org.example.application.utils.Loginfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //以列表形返回表中所有用户信息
    public List<User> getAllUser() {
        String sql = "select * from users";
        List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user._id = rs.getInt(1);
                user.username = rs.getString(2);
                user.password = rs.getString(3);
                user.role = rs.getInt(4);
                user.create_time = rs.getDate(5);
                System.out.println(user.toString());
                return user;
            }
        });
        return list;
    }

    //通过用户名放回用户信息
    public UserLogin getUserByUserName(String username){
        UserLogin userlogin = new UserLogin();
        String sql = "select * from users where username=?";
        jdbcTemplate.query(sql, new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                userlogin.username = rs.getString(2);
                userlogin.password = rs.getString(3);
                userlogin.role = rs.getInt(4);
                Loginfo.show(Loginfo.PART_USER,userlogin.toString());
            }
        });
        return userlogin.username!=null?userlogin:null;
    }
}
