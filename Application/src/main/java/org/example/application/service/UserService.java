package org.example.application.service;

import org.example.application.dao.UserLogin;
import org.example.application.entity.User;
import org.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUser();
    }

    //获取用户密码和角色信息
    public UserLogin FindPasswordRoleByUsername(String username){
        return userRepository.getUserByUserName(username);
    }

}
