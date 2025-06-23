package com.kasunjay.springsecurity.config;

import com.kasunjay.springsecurity.exception.CustomAccessDeniedHandler;
import com.kasunjay.springsecurity.exception.CustomBasicAuthenticationEntryPoint;
import com.kasunjay.springsecurity.filter.AuthoritiesLoggingAfterFilter;
import com.kasunjay.springsecurity.filter.AuthoritiesLoggingAtFilter;
import com.kasunjay.springsecurity.filter.CsrfCookieFilter;
import com.kasunjay.springsecurity.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;

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
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

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
                    config.setMaxAge(3600L); // Cache preflight response for 1 hour, this used to reduce the number of preflight requests
                    return config;
                }
            }));
        } else {
            http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4300")); // Allow requests from this origin
                    config.setAllowedMethods(Collections.singletonList("*")); // Allow all HTTP methods
                    config.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
                    config.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
                    config.setMaxAge(3600L); // Cache preflight response for 1 hour, this used to reduce the number of preflight requests
                    return config;
                }
            }));

        }

        http
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false)) // Disable explicit save of SecurityContext
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // Always create a session
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // Custom filter to validate Authorization headers before authentication
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class) // Custom filter to log authorities after authentication
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class) // Custom filter to log authorities at the time of authentication
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
//                        .requestMatchers("/myBalance").hasAnyAuthority("VIEWBALANCE", "VIEWACCOUNT")
//                        .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
//                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
                        .requestMatchers("/myAccount").hasRole("USER")
                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/myLoans").hasRole("USER")
                        .requestMatchers("/myCards").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
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
