package com.appsdeveloperblog.ws.api.resourceserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityBean  {
/*    @Bean
    public JwtAuthenticationConverter customJwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return converter;
    }*/


/*    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/users/status/check")
                .hasAuthority("SCOPE_profile")
                .anyRequest()
                .authenticated().
                and().oauth2ResourceServer()
                .jwt();
        return http.build();

    }*/

    @Bean
    public SecurityFilterChain configure2(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/users/**").
                //access(new WebExpressionAuthorizationManager("hasAuthority('SCOPE_profile') and hasAuthority('SCOPE_status')")).
        hasRole("developer"). // When using hasRole() method we just need to specify the role required.
        //hasAuthority("ROLE_developer").// When using hasAuthority() method "ROLE_" needs to be added as Spring won't do it for us
                anyRequest().
                authenticated().
                and().oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(converter);

        return http.build();

    }

}

