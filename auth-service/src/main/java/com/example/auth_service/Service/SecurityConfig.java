package com.example.auth_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    



    @Autowired
    JwtFilter filter;
    @Bean
    public  SecurityFilterChain securityfilterchain(HttpSecurity http){
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> 
            requests
            //.requestMatchers(HttpMethod.GET).hasAuthority("ADMIN")
        .anyRequest().permitAll())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }


    @Bean
    public PasswordEncoder passwordencoder(){
        return NoOpPasswordEncoder.getInstance();
    }
   @Bean
public AuthenticationProvider authenticationProvider(
        CustomUserDetailsService customUserDetailsService,
        PasswordEncoder passwordEncoder) {

    DaoAuthenticationProvider provider =
            new DaoAuthenticationProvider(customUserDetailsService);

    provider.setPasswordEncoder(passwordencoder());

    return provider;
}

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
}
