package com.example.demo.controller;

import com.example.demo.dto.user.OutputUserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<OutputUserDto> getAllUsers() {
        List<User> users = this.userService.findAll();
        return this.userMapper.toDtoList(users);
    }

    @GetMapping("/{id}")
    public OutputUserDto getUser(@PathVariable("id") UUID id) {
        User user = this.userService.findOne(id);
        return this.userMapper.toDto(user);
    }
}
