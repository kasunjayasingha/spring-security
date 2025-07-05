package com.kasunjay.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.config
 * @Class: ProjectSecurityConfig
 * @Created on: 5/28/2025 at 11:56 PM
 */
@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/secure").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

//    @Bean
//    ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration github = githubClientRegistration();
//        ClientRegistration google = googleClientRegistration();
//        return new InMemoryClientRegistrationRepository(github, google);
//    }
//
//    private ClientRegistration githubClientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23liF0GqebkV3q69NU")
//                .clientSecret("ce234f7edd8db05565c8a71c17db64bfa495f2b0").build();
//    }
//
//
//    private ClientRegistration googleClientRegistration() {
//        return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId("1025981105626-4kj87m4911kl4vq0c0l0rqd4vpgba788.apps.googleusercontent.com")
//                .clientSecret("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").build();
//    }

}
