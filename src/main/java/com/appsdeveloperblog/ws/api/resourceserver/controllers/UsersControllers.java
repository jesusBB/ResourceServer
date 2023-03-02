package com.appsdeveloperblog.ws.api.resourceserver.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UsersControllers {

    @GetMapping("/status/check")
    @PreAuthorize("hasAuthority('ROLE_developer')") //and hasAuthority('SCOPE_profile') and hasAuthority('SCOPE_status')
    public String status(){
        return "working ...";
    }

    //@Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return id + " will be deleted and user from access token is " + jwt.getSubject();
    }
}
