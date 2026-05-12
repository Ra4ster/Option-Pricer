package com.ra4ster.roserank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Usually disabled for stateless APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/webhooks/**").permitAll() // Webhooks must be public
                .requestMatchers("/api/users/me").authenticated() // Profile must be private
                .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(_ -> {})); // Standard JWT validation
            
        return http.build();
    }
}