package com.example.auth_service.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.example.auth_service.Config.LoginDto;
import com.example.auth_service.Config.UserFeignClient;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        LoginDto user =userFeignClient.login(email);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
