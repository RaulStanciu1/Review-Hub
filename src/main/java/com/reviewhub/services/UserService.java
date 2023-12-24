package com.reviewhub.services;

import com.reviewhub.entities.User;
import com.reviewhub.respository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public ResponseEntity<?> createUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
        return ResponseEntity.ok("User created");
    }

    public String getUserByUsername(String user1) {
        return userRepository.findByName(user1).toString();
    }

    public ResponseEntity<?> checkUser(String username, String password) {
        Boolean ok = userRepository.findByNameAndPassword(username, password) != null;
        if (ok) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Login failed");
    }

    public ResponseEntity<?> getSubscriptionStatus(String username) {
        String type = userRepository.findByName(username).getType();
        return ResponseEntity.ok(type);
    }

    public void upgrade(String username, String type) {
        User user = userRepository.findByName(username);
        user.setType(type);
        userRepository.save(user);
    }
}

