package com.reviewhub.services;

import com.reviewhub.entities.User;
import com.reviewhub.respository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public void createUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }

    public String getUserByUsername(String user1) {
        return userRepository.findByName(user1).toString();
    }

    public boolean checkUser(String username, String password) {
        return userRepository.findByNameAndPassword(username, password) != null;
    }
}

