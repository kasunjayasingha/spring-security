package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Class: UserDetailsSericeImpl
 * @Created on: 6/5/2025 at 4:44 PM
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
        * This method is used by Spring Security to load user details by username (email in this case).
        * It retrieves the user from the database using the CustomerRepository,
        * and if found, it creates a UserDetails object with the user's email, password, and authorities.
        * If the user is not found, it throws a UsernameNotFoundException.
        * @param username the email of the user to be loaded
        * @return UserDetails object containing user information
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + username));

        List<GrantedAuthority> authorities = customer.getAuthorities().stream().map(authority -> new
                SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
