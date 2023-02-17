package com.ensab.mediatech.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // pour dire que ce class une class de configuration de sécurité
public class SecurityConfig {

    // this its own  SecurityFilterChain
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
                http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return  http.build();
    }
}
