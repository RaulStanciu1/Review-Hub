package com.reviewhub.controller;

import com.reviewhub.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing user-related operations.
 */
@RestController
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService The UserService to be injected.
     */
    public UserController(UserService userService) { this.userService = userService; }

    /**
     * Register a new user.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @return A success message upon successful registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.createUser(username, password);

    }

    /**
     * Log in a user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the login is successful, false otherwise.
     */
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.checkUser(username, password);
    }

    /**
     * Check the account status of a user.
     *
     * @param username The username of the user.
     * @return The subscription status of the user.
     */
    @GetMapping("/checkAccount")
    public ResponseEntity<?> checkAccount(@RequestParam("username") String username) {
        return userService.getSubscriptionStatus(username);
    }

    /**
     * Upgrade the account type of user.
     *
     * @param username The username of the user.
     * @param type The new account type.
     * @return A success message upon successful upgrade.
     */
    @PutMapping("/upgrade")
    public ResponseEntity<?> upgrade(@RequestParam("username") String username, @RequestParam("type") String type) {
        userService.upgrade(username, type);
        return ResponseEntity.ok("Upgrade successful");
    }
}
