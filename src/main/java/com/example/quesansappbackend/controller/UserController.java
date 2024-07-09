package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        //custom exception
        return userRepository.findById(userId).orElse(null);
    }
}
