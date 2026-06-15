package com.example.auth_service.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils utils;
    @Autowired
    CustomUserDetailsService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token=null;
        String username=null;
        String header=request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer ")){
            token=header.substring(7);
            username=utils.extractUsername(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails use=customUserDetailService.loadUserByUsername(username);


            if(utils.isValidToken(use,token)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(use,null, use.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    
    }
    
}
