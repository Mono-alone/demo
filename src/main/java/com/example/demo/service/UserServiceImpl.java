package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ApplicationException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User findOne(UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
    }
}
