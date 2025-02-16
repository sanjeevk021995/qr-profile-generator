package qr_profile_generator.service;
package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        logger.info("Signing up user: {}", user.getEmail());
        // Hash the password before saving
        // user.setPassword(passwordEncoder.encode(user.getPassword()));  // Use BCrypt or similar
        return userRepository.save(user);
    }

    public Optional<User> login(String usernameOrEmail, String password) {
        logger.info("Logging in user: {}", usernameOrEmail);
        Optional<User> user = userRepository.findByEmail(usernameOrEmail); // Or find by mobile
        if (user.isPresent() /*&& passwordEncoder.matches(password, user.get().getPassword())*/) { // Check password
            return user;
        }
        return Optional.empty();
    }
}
