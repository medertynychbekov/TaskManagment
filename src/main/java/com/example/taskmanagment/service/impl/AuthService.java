package com.example.taskmanagment.service.impl;

import com.example.taskmanagment.exceptions.EntityNotFoundException;
import com.example.taskmanagment.model.dto.UserRequest;
import com.example.taskmanagment.model.dto.UserResponse;
import com.example.taskmanagment.model.dto.mapper.impl.UserMapper;
import com.example.taskmanagment.model.entity.Role;
import com.example.taskmanagment.model.entity.User;
import com.example.taskmanagment.repository.UserRepository;
import com.example.taskmanagment.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements ServiceLayer<UserRequest, UserResponse> {

    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = userMapper.mapToEntity(userRequest);
        Role role;
        if (user.getName().equals("admin")) {
            role = new Role("ADMIN");
        } else {
            role = new Role("USER");
        }
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.mapToResponse(byId(id));
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = byId(id);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse delete(Long id) {
        User user = byId(id);
        userRepository.delete(user);
        return userMapper.mapToResponse(user);
    }

    private User byId(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id=" + id + " not found!!!")
        );
    }
}
