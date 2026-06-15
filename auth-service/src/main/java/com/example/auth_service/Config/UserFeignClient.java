package com.example.auth_service.Config;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface UserFeignClient {
    
    @GetMapping("/api/users/login/{email}")
   LoginDto login(@PathVariable String email);
}
