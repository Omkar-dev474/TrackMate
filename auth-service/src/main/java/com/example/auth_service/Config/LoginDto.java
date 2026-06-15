package com.example.auth_service.Config;

import lombok.Data;

@Data
public class LoginDto {
    private Long userProfileId;
   private String email;
    private String role;
    private String password;
}
