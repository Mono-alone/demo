package com.example.demo.controller;

import com.example.demo.dto.user.InputUserDto;
import com.example.demo.dto.user.OutputUserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<OutputUserDto> findAllUsers() {
        List<User> users = this.userService.findAll();
        return this.userMapper.toDtoList(users);
    }

    @GetMapping("/{id}")
    public OutputUserDto findUser(@PathVariable("id") UUID id) {
        User user = this.userService.findOne(id);
        return this.userMapper.toDto(user);
    }

    @PostMapping
    public OutputUserDto addUser(@RequestBody @Valid InputUserDto userDto) {
        User addedUser = this.userService.addUser(this.userMapper.fromDto(userDto));
        return this.userMapper.toDto(addedUser);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
