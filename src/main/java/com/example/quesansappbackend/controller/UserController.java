package com.example.quesansappbackend.controller;

import com.example.quesansappbackend.entity.User;
import com.example.quesansappbackend.exceptions.UserNotFoundException;
import com.example.quesansappbackend.repository.UserRepository;
import com.example.quesansappbackend.response.UserResponse;
import com.example.quesansappbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {

        return userService.saveOneUser(newUser);
    }
    @GetMapping("/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) {
        User user = (userService.getOneUserById(userId));
        if(user==null) {
            throw new UserNotFoundException("User not found");
        }
        return new UserResponse(user);
    }
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
       return userService.updateOneUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId) {

        userService.deleteOneUser(userId);
    }
    @GetMapping("/activity/{userId}")
    public List<Object> getUserActivvity(@PathVariable Long userId) {

        return userService.getUserActivity(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound() {

    }


}
