package com.example.gateway_service;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String hello() {
        return "Gateway Protected";
    }



    @GetMapping("/profile")
public Map<String, Object> profile(
        @AuthenticationPrincipal Jwt jwt) {

    return Map.of(
            "username", jwt.getClaim("preferred_username"),
            "email", jwt.getClaim("email"),
            "sub", jwt.getSubject(),
            "roles", jwt.getClaim("realm_access")
    );
}
}
