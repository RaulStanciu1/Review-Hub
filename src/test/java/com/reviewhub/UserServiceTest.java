package com.reviewhub;

import com.reviewhub.respository.UserRepository;
import com.reviewhub.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // Create a sample user
        userService.createUser("user1", "password1");

        // Retrieve the user from MongoDB

        // Perform assertions or logging to verify the correctness of the created and retrieved users
        // For example:
        System.out.println("Created User: " + userService.getUserByUsername("user1"));
    }

    @Test
    public void testCheckUser() {
        // Create a sample user
        if (userService.checkUser("user1", "password1")) {
            System.out.println("User exists");
        } else {
            System.out.println("User does not exist");
        }

        if (userService.checkUser("user2", "password2")) {
            System.out.println("User exists");
        } else {
            System.out.println("User does not exist");
        }

        userRepository.deleteByName("user1");

    }

}
