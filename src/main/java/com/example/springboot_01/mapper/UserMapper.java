package com.example.springboot_01.mapper;

import com.example.springboot_01.dto.UserDto;
import com.example.springboot_01.dto.UserResponseDto;
import com.example.springboot_01.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserMapper {


    public User toEntity (UserDto dto){
        User entity = new User();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }


    public UserResponseDto toDto(User entity){
        UserResponseDto dto = new UserResponseDto();
        dto.setRole(entity.getRole());
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());

        return dto;
    }

    public List<UserResponseDto> toDtoList (List<User> entities){
        if(entities == null || entities.isEmpty()){
            return new ArrayList<>();
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }
}
