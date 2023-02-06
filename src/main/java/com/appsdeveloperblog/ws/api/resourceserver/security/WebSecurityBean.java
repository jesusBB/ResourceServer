package com.appsdeveloperblog.ws.api.resourceserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class WebSecurityBean  {

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

        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/users/**").
                access(new WebExpressionAuthorizationManager("hasAuthority('SCOPE_profile') and hasAuthority('SCOPE_status')")).
                anyRequest().
                authenticated().
                and().oauth2ResourceServer()
                .jwt();

        return http.build();

    }

}

