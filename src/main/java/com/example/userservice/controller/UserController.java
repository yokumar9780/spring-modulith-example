package com.example.userservice.controller;

import com.example.shared.model.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @PutMapping("/profile")
    public String updateProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }
}
