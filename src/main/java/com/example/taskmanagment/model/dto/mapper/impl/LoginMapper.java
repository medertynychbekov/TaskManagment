package com.example.taskmanagment.model.dto.mapper.impl;

import com.example.taskmanagment.model.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginResponse mapToResponse(String token, String roleName) {
        return LoginResponse.builder()
                .token(token)
                .roleName(roleName)
                .build();
    }
}
