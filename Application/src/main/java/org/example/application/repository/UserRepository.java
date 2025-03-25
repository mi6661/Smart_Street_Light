package org.example.application.repository;

import org.example.application.dao.UserInfo;
import org.example.application.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository  {

    public List<User> getAllUser();
    public UserInfo getUserByUserName(String username);
    public boolean insert(UserInfo infos);
}
