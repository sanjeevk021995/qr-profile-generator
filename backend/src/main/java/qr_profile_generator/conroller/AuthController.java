package qr_profile_generator.conroller;

package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        logger.info("Received signup request for user: {}", user.getEmail());
        User createdUser = authService.signup(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String usernameOrEmail, @RequestParam String password) {
        logger.info("Received login request for user: {}", usernameOrEmail);
        return authService.login(usernameOrEmail, password)
                .map(user -> ResponseEntity.ok(user)) // Return user on success
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()); // 401 Unauthorized
    }
}
