package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.request.UserRequest;
import com.example.quesansappbackend.security.JwtTokenProvider;
import com.example.quesansappbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer "+jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
        if(userService.getOneUserByUserName(registerRequest.getUserName()) !=null) {
            return new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(registerRequest.getPassword());
    }
}
