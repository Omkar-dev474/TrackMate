package com.example.gateway_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {

        // return http
        //         .csrf(ServerHttpSecurity.CsrfSpec::disable)
        //         .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        //         .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

        //         .authorizeExchange(exchange -> exchange

        //                 .pathMatchers("/api/login/**")
        //                 .permitAll()

        //                 .anyExchange()
        //                 .authenticated())

        //         .build();
        

      
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

            .authorizeExchange(exchange -> exchange
                    .anyExchange().permitAll())

            .build();


        // return http
        //         .csrf(ServerHttpSecurity.CsrfSpec::disable)
        //         .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        //         .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)

        //         .authorizeExchange(exchange -> exchange

        //                 .pathMatchers("/api/login/**")
        //                 .permitAll()

        //                 .pathMatchers("/admin/**")
        //                 .hasRole("ADMIN")

        //                 .pathMatchers(HttpMethod.POST,"/api/shipments/**")
        //                 .hasAuthority("USERS")

        //                 .anyExchange()
        //                 .authenticated())

        //         .build();



        // return http
        //     .csrf(ServerHttpSecurity.CsrfSpec::disable)
        //     .authorizeExchange(exchange ->
        //             exchange.anyExchange().permitAll())
        //     .build();
    }
}