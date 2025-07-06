package com.kasunjay.springsecurity.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.config
 * @Class: KeycloakRoleConverter
 * @Created on: 7/6/2025 at 10:33 AM
 */
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Object realmAccessObj = source.getClaims().get("realm_access");

        // Check if realmAccessObj is an instance of Map
        if (!(realmAccessObj instanceof Map<?, ?> realmAccessMap)) {
            return new ArrayList<>();
        }

        // Check if roles key exists and is a List
        Object rolesObj = realmAccessMap.get("roles");
        if (!(rolesObj instanceof List<?> rolesList)) {
            return new ArrayList<>();
        }

        // Convert roles to GrantedAuthority
        return rolesList.stream()
                .filter(role -> role instanceof String)
                .map(role -> "ROLE_" + role)
                .map(role -> new SimpleGrantedAuthority((String) role))
                .collect(Collectors.toList());
    }
}
