package com.appsdeveloperblog.ws.api.resourceserver.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UsersControllers {

    @GetMapping("/status/check")
    @PreAuthorize("hasAuthority('profile') and hasAuthority('status')")
    public String status(){
        return "working ...";
    }

    @Secured("ROLE_developer")
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        return id + " will be deleted";
    }
}
