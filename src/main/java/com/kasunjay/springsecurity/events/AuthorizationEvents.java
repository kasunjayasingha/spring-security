package com.kasunjay.springsecurity.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.events
 * @Class: AuthorizationEvents
 * @Created on: 6/19/2025 at 11:17 AM
 */
@Component
@Slf4j
public class AuthorizationEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent deniedEvent) {
        log.error("Authorization failed for the user : {} due to : {}", deniedEvent.getAuthentication().get().getName(),
                deniedEvent.getAuthorizationResult().toString());
    }

}