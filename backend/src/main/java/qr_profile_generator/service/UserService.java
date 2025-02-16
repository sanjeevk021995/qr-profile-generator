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
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QRService qrService; // Inject QRService


    public User updateProfile(String id, User updatedUser) {
        logger.info("Updating profile for user ID: {}", id);
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update fields (name, age, etc.)
            user.setName(updatedUser.getName());
            user.setAge(updatedUser.getAge());
            // ... other fields
            String qrCodePath = qrService.generateQRCode(user); // Generate and get path
            user.setProfilePhoto(qrCodePath); // Store QR code path or Base64
            return userRepository.save(user);
        }
        return null; // Or throw an exception
    }

    public Optional<User> getUser(String id) {
        logger.info("Fetching profile for user ID: {}", id);
        return userRepository.findById(id);
    }
}
