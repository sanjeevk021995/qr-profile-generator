package qr_profile_generator.config;

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for API (Important!)
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/auth/**").permitAll() // Allow auth endpoints
                        .antMatchers("/user/**").hasRole("USER") // User role access
                        .antMatchers("/admin/**").hasRole("ADMIN") // Admin role access
                        .anyRequest().authenticated()
                )
                .httpBasic(); // Basic Authentication (for now - use JWT in real app)
        return http.build();
    }

    // ... other security configurations (e.g., password encoder)
}

