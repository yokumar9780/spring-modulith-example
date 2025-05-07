package com.example.userservice.service;

import com.example.shared.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String updateUserProfile(User user) {
        userRepository.save(user);
        return "User profile updated successfully!";
    }
}
