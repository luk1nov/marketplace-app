package com.lukyanov.itemservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private static final String AUTHORITIES = "authorities";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> roles = (List<String>) jwt.getClaims().get(AUTHORITIES);
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }
        return roles.stream().map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role)).toList();
    }
}