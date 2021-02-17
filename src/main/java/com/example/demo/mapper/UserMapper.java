package com.example.demo.mapper;

import com.example.demo.dto.user.InputUserDto;
import com.example.demo.dto.user.OutputUserDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    OutputUserDto toDto(User user);
    List<OutputUserDto> toDtoList(List<User> userList);

    User fromDto(InputUserDto userDto);
}
