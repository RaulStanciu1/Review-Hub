package com.reviewhub.controller;

import com.reviewhub.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public String register(@Param("username") String username, @Param("password") String password) {
        userService.createUser(username, password);
        return "Success!";
    }

    @GetMapping("/login")
    public boolean login(@Param("username") String username, @Param("password") String password) {
        return userService.checkUser(username, password);
    }

}
