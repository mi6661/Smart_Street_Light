package org.example.application.controller;


import org.example.application.dao.UserInfo;
import org.example.application.entity.User;
import org.example.application.response.ApiResonse;
import org.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    public ApiResonse<UserInfo> login(@RequestParam String username, @RequestParam String password){
        UserInfo userLogin = userService.FindPasswordRoleByUsername(username);
        if(userLogin == null) return ApiResonse.fail("用户不存在");

        //判断密码是否争取
        if(password.equals(userLogin.password)){
            return ApiResonse.success(userLogin);
        }else{
            return ApiResonse.fail("账号或密码错误");
        }
    }
    @PostMapping("/regist")
    public ApiResonse<Boolean> regist(@RequestParam String username,@RequestParam String password,@RequestParam int role){
        if (userService.Register(username,password,role)) return ApiResonse.success(true);
        return ApiResonse.fail("注册失败");
    }
}
