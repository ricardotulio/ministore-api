package com.ministore.api.services;

import org.springframework.stereotype.Service;

import com.ministore.api.entities.User;
import com.ministore.api.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return this.userRepository.save(user);
    }
    
}
