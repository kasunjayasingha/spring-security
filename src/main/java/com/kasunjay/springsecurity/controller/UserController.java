package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: UserController
 * @Created on: 6/5/2025 at 8:37 PM
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Customer customer) {
        return userService.registerUser(customer);
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userService.getUserDetails(username);
        } else {
            return null; // or throw an exception
        }

    }
}
