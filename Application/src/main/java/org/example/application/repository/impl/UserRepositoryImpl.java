package org.example.application.repository.impl;

import org.example.application.entity.User;
import org.example.application.repository.UserRepository;
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

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, JdbcClient jdbcClient) {
        this.jdbcTemplate = jdbcTemplate;
    }


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


}
