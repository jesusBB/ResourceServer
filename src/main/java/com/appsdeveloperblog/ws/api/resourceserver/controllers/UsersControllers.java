package com.appsdeveloperblog.ws.api.resourceserver.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('profile') and hasAuthority('status')")
public class UsersControllers {

    @GetMapping("/status/check")
    public String status(){
        return "working ...";
    }
}
