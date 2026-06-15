package com.example.auth_service.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.auth_service.Config.LoginDto;
import com.example.auth_service.Config.UserFeignClient;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtService;
    private final UserFeignClient userFeignClient;

    public String create(LoginDto loginUser) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.getEmail(),
                                loginUser.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {

            LoginDto user =
                    userFeignClient.login(loginUser.getEmail());

            return jwtService.generateToken(
                    user.getUserProfileId(),
                    user.getEmail(),
                    user.getRole()
            );
        }

        throw new RuntimeException("Invalid credentials");
    }
}

