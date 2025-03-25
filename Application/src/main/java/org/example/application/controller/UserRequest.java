package org.example.application.controller;


import org.example.application.entity.User;
import org.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRequest {

    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/list")
    public List<User> list(){
        return userService.getAllUsers();
    }
}
