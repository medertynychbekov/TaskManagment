package com.example.taskmanagment.controller;


import com.example.taskmanagment.config.jwt.JwtUtil;
import com.example.taskmanagment.model.dto.LoginRequest;
import com.example.taskmanagment.model.dto.LoginResponse;
import com.example.taskmanagment.model.dto.UserRequest;
import com.example.taskmanagment.model.dto.UserResponse;
import com.example.taskmanagment.model.dto.mapper.impl.LoginMapper;
import com.example.taskmanagment.model.entity.User;
import com.example.taskmanagment.repository.UserRepository;
import com.example.taskmanagment.service.impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "this controller class for authorization and authentication")
public class AuthController {

    AuthService authService;
    UserRepository userRepository;
    LoginMapper loginMapper;
    JwtUtil jwtUtil;
    UserDetailsService userDetailsService;

    @PostMapping("/sing-up")
    @Operation(description = "this method for save User object")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(authService.save(userRequest), HttpStatus.OK);
    }

    @PostMapping("/sing-in")
    @Operation(description = "this method help for get the JWT token")
    public ResponseEntity<LoginResponse> singIn(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loginMapper.mapToResponse(jwtUtil.generateToken(userDetailsService.loadUserByUsername(loginRequest.getUsername())), user.getRoles().toString()), HttpStatus.OK);
    }

}
