package com.ensab.mediatech.config;

import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String jwtToken;



        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request ,response);
            return;
        }
        /*
        jwtToken = authHeader.substring(7);
        userEmail = "something"; // TODO to be implemented
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            CachingUserDetailsService userDetailsService;
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        }

         */
    }
}
