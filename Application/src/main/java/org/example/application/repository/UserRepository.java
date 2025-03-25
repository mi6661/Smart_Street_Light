package org.example.application.repository;

import org.example.application.dao.UserLogin;
import org.example.application.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserRepository  {

    public List<User> getAllUser();
    public UserLogin getUserByUserName(String username);
}
