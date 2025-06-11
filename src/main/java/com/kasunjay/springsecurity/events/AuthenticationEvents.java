package com.kasunjay.springsecurity.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.events
 * @Class: AuthenticationEvents
 * @Created on: 6/11/2025 at 3:18 PM
 */
@Component
@Slf4j
public class AuthenticationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("Login successful for the user : {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        log.error("Login failed for the user : {} due to : {}", failureEvent.getAuthentication().getName(),
                failureEvent.getException().getMessage());
    }
}
