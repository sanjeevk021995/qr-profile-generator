package qr_profile_generator.conroller;

package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @RestController
    @RequestMapping("/user")
    public class UserController {

        private static final Logger logger = LoggerFactory.getLogger(UserController.class);

        @Autowired
        private UserService userService;

        @PutMapping("/{id}") // Update profile
        public ResponseEntity<User> updateProfile(@PathVariable String id, @RequestBody User updatedUser) {
            logger.info("Received update profile request for user ID: {}", id);
            User user = userService.updateProfile(id, updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/{id}") // Get profile
        public ResponseEntity<User> getUser(@PathVariable String id) {
            logger.info("Received get profile request for user ID: {}", id);
            return userService.getUser(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // ... other user-related endpoints if needed
    }

