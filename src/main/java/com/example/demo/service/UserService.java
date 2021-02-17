package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {

    List<User> findAll();
    User findOne(UUID id);
    User addUser(User user);
}
