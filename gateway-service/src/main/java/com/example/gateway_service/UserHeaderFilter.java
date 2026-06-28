package com.example.gateway_service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class UserHeaderFilter implements GlobalFilter {

   @Override
public Mono<Void> filter(
        ServerWebExchange exchange,
        GatewayFilterChain chain) {

    return exchange.getPrincipal()
            .cast(Authentication.class)
            .flatMap(auth -> {

                if (auth instanceof JwtAuthenticationToken jwtAuth) {

                    Jwt jwt = jwtAuth.getToken();

                    Map<String, Object> realmAccess =
                            jwt.getClaim("realm_access");

                    List<String> roles =
                            (List<String>) realmAccess.get("roles");

                    String role = String.join(",", roles);

                    ServerHttpRequest request = exchange.getRequest()
                            .mutate()
                            .header("X-USER-ID", jwt.getSubject())
                            .header("X-USER-NAME",
                                    jwt.getClaimAsString("preferred_username"))
                            .header("X-USER-EMAIL",
                                    jwt.getClaimAsString("email"))
                            .header("X-USER-ROLE", role)
                            .build();

                    return chain.filter(
                            exchange.mutate()
                                    .request(request)
                                    .build());
                }

                return chain.filter(exchange);
            })
            .switchIfEmpty(chain.filter(exchange));
}
}