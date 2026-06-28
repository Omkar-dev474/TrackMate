package com.example.shipments_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {

     @GetMapping("/api/users/{keycloakUserid}/exists")
    boolean checkUserExists(@PathVariable String keycloakUserid);

    @GetMapping("/api/users/{keycloakUserid}")
    ResponseUserDto getUserById(@PathVariable String keycloakUserid);
}
