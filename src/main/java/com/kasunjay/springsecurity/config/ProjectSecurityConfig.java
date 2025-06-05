package com.kasunjay.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.config
 * @Class: ProjectSecurityConfig
 * @Created on: 5/28/2025 at 11:56 PM
 */
@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
    // This bean is used to create in-memory users for testing purposes
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user").password("{noop}Kasun@epic@12345").authorities("read").build();
//        UserDetails admin = User.withUsername("admin")
//                .password("{bcrypt}$2a$12$es3bRC4OlCzvwlc6nutEOOlTyGVxQEXu8Udt3Nt0oz9e73XdFlcsS") // Password: Kasun@epic@12345
//                .authorities("admin").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    // This bean is used to create users from a database using JDBC
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * From Spring Security 6.3 version
     * This bean is used to check if the password has been compromised, Which means it has been exposed in a data breach.
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
