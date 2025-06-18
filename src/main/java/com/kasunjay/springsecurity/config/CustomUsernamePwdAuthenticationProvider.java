package com.kasunjay.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.config
 * @Class: CustomUsernamePwdAuthenticationProvider
 * @Created on: 6/7/2025 at 1:45 PM
 */
@Component
@RequiredArgsConstructor
public class CustomUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Check if the prod profile is active
        boolean isProdProfileActive = Arrays.asList(environment.getActiveProfiles()).contains("default");

        // Only check password if prod profile is active
        if (isProdProfileActive) {
            // Check if the password matches
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                /*
                 * If the password matches, create an authentication token.
                 * The UsernamePasswordAuthenticationToken is a built-in implementation of Authentication.
                 * It contains the user's details, credentials, and authorities.
                 * Also, if you want to add additional authentication flows, you can do it here.
                 */
                // If the password matches, return an authenticated token
                return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userDetails, password, userDetails.getAuthorities());
            } else {
                throw new AuthenticationException("Invalid username or password") {};
            }
        } else {
            // If not in prod profile, skip password check and authenticate directly
            return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    userDetails, password, userDetails.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
