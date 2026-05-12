package com.ra4ster.roserank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra4ster.roserank.model.user.User;
import com.ra4ster.roserank.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(JwtAuthenticationToken auth) {
        String clerkId = auth.getToken().getSubject();

        return userService.getUserByClerkId(clerkId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}