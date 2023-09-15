package com.example.taskmanagment.model.dto.mapper.impl;

import com.example.taskmanagment.model.dto.UserRequest;
import com.example.taskmanagment.model.dto.UserResponse;
import com.example.taskmanagment.model.dto.mapper.Mapper;
import com.example.taskmanagment.model.entity.Role;
import com.example.taskmanagment.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper implements Mapper<UserRequest, User, UserResponse> {

    @Override
    public User mapToEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .created(LocalDateTime.now())
                .build();
    }

    @Override
    public UserResponse mapToResponse(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .created(entity.getCreated())
                .roleName(entity.getRoles().stream().map(Role::getName).toString())
                .build();
    }
}
