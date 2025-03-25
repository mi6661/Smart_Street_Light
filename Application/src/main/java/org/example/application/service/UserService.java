package org.example.application.service;

import org.example.application.dao.UserInfo;
import org.example.application.entity.User;
import org.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUser();
    }

    //获取用户密码和角色信息
    public UserInfo FindPasswordRoleByUsername(String username){
        return userRepository.getUserByUserName(username);
    }

    //判断密码是否正确
    public boolean IsPasswordRight(String username, String password){
        UserInfo userInfo = userRepository.getUserByUserName(username);
        return password.equals(userInfo.password);
    }

    //用户是否已经存在
    public Boolean UsernameExist(String username){
        return userRepository.getUserByUserName(username) != null;
    }


    //用户注册
    public boolean Register(String username,String password,int role){
        if(UsernameExist(username)) return false;
        UserInfo userInfo = new UserInfo();
        userInfo.username = username;
        userInfo.password = password;
        userInfo.role = role;
        return userRepository.insert(userInfo);
    }
    //修改密码
    public boolean ChangePassword(String username,String oldPassword,String newPassword){
        //用户不存在
        if(UsernameExist(username)) return false;
        //判断用户原密码是否正确
        if(!IsPasswordRight(username,oldPassword)) return false;
        UserInfo userinfo = new UserInfo();
        UserInfo userInfo = userRepository.getUserByUserName(username);
        userInfo.password = newPassword;
        return userRepository.insert(userinfo);
    }
}
