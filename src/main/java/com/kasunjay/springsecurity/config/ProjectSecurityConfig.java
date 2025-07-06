package com.kasunjay.springsecurity.config;

import com.kasunjay.springsecurity.exception.CustomAccessDeniedHandler;
import com.kasunjay.springsecurity.exception.CustomBasicAuthenticationEntryPoint;
import com.kasunjay.springsecurity.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.config
 * @Class: ProjectSecurityConfig
 * @Created on: 5/28/2025 at 11:56 PM
 */
@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final Environment environment;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter(); // Create a JwtAuthenticationConverter to convert JWT tokens to Authentication objects
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // Set a custom converter to extract roles from the JWT token

        if (environment.matchesProfiles("prod")) {
            // Only force HTTPS in production environment
            http.redirectToHttps(withDefaults()); // Force HTTPS in production
            http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("https://localhost:4200")); // Allow requests from this origin
                    config.setAllowedMethods(Collections.singletonList("*")); // Allow all HTTP methods
                    config.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
                    config.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
                    config.setExposedHeaders(Arrays.asList("Authorization")); // Expose Authorization header to the client
                    config.setMaxAge(3600L); // Cache preflight response for 1 hour, this used to reduce the number of preflight requests
                    return config;
                }
            }));
        } else {
            http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Allow requests from this origin
                    config.setAllowedMethods(Collections.singletonList("*")); // Allow all HTTP methods
                    config.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
                    config.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
                    config.setExposedHeaders(Arrays.asList("Authorization")); // Expose Authorization header to the client
                    config.setMaxAge(3600L); // Cache preflight response for 1 hour, this used to reduce the number of preflight requests
                    return config;
                }
            }));

        }

        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session management to stateless (no sessions will be created or used by Spring Security)
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                        .requestMatchers("/myBalance").hasAnyAuthority("VIEWBALANCE", "VIEWACCOUNT")
                        .requestMatchers("/myLoans").authenticated()
                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
//                        .requestMatchers("/myAccount").hasRole("USER")
//                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/myLoans").hasRole("USER")
//                        .requestMatchers("/myCards").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/notices", "/contact", "/error", "/register").permitAll());
        http.oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer ->
                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }


}
