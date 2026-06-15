package com.example.auth_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.Config.LoginDto;
import com.example.auth_service.Service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginservice;

    @PostMapping("/auth")
    public ResponseEntity<?> tokenCreation(@RequestBody LoginDto loginuser){
        try{
            String token=loginservice.create(loginuser);
            if(token.isEmpty()||token==null){
                return ResponseEntity.badRequest().body("Failed to create token");
            }
            return ResponseEntity.ok(token);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
