package com.appsdeveloperblog.ws.api.resourceserver.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> roles = (Map<String, Object>) source.getClaims().get("realm_access");

        if(roles == null || roles.isEmpty())
            return Collections.EMPTY_LIST;

        List<String> rolesList = (List<String>) roles.get("roles");
        List<GrantedAuthority> authorities = rolesList.stream().map(role -> "ROLE_" + role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return authorities;
    }
}
