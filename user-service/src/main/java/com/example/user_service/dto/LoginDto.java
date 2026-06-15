package com.example.user_service.dto;

import lombok.Data;

@Data
public class LoginDto {
    private Long userProfileId;
   private String email;
    private String role;
    private String password;
}
