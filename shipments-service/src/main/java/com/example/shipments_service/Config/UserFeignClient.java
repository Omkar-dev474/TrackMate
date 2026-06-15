package com.example.shipments_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {

     @GetMapping("/api/users/{userId}/exists")
    boolean checkUserExists(@PathVariable String userId);

    @GetMapping("/api/users/{userId}")
    ResponseUserDto getUserById(@PathVariable String userId);
}
