package qr_profile_generator.conroller;

package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/dashboard")
    public String adminDashboard() {
        logger.info("Admin accessed dashboard");
        return "Admin Dashboard"; // Replace with actual logic
    }

    // ... other admin-related endpoints
}
