package com.example.gateway_service;

import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

        private final JwtUtils jwtUtils;

        @Override
        public Mono<Void> filter(
                        ServerWebExchange exchange,
                        GatewayFilterChain chain) {

                String authHeader = exchange.getRequest()
                                .getHeaders()
                                .getFirst("Authorization");

                if (authHeader == null ||
                                !authHeader.startsWith("Bearer ")) {

                        return chain.filter(exchange);
                }

                String token = authHeader.substring(7);

                if (!jwtUtils.validateToken(token)) {
                        exchange.getResponse()
                                        .setStatusCode(HttpStatus.UNAUTHORIZED);

                        return exchange.getResponse().setComplete();
                }

                String email = jwtUtils.extractUsername(token);
                String role = jwtUtils.extractRole(token);
                Long userId = jwtUtils.extractUserId(token);
                String path = exchange.getRequest()
                                .getURI()
                                .getPath();

                switch (role) {

                        case "ADMIN":
                                // access everywhere
                                break;

                        case "USERS":
                                if (path.startsWith("/admin")) {
                                        exchange.getResponse()
                                                        .setStatusCode(HttpStatus.FORBIDDEN);
                                        return exchange.getResponse().setComplete();
                                }
                                break;

                        default:
                                exchange.getResponse()
                                                .setStatusCode(HttpStatus.FORBIDDEN);
                                return exchange.getResponse().setComplete();

                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(
                                                new SimpleGrantedAuthority(
                                                                "ROLE_" + role)));

                SecurityContextImpl securityContext = new SecurityContextImpl(authentication);

                ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(
                                                exchange.getRequest()
                                                                .mutate()
                                                                .header("X-USER-ID",
                                                                                String.valueOf(userId))
                                                                .header("X-USER-EMAIL",
                                                                                email)
                                                                .header("X-USER-ROLE",
                                                                                role)
                                                                .build())
                                .build();

                return chain.filter(mutatedExchange)
                                .contextWrite(
                                                ReactiveSecurityContextHolder
                                                                .withSecurityContext(
                                                                                Mono.just(securityContext)));
        }
}