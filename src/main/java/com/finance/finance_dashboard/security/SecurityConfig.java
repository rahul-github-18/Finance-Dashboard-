package com.finance.finance_dashboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()

                        // User management
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // Financial Records
                        .requestMatchers(HttpMethod.GET, "/api/records/**")
                        .hasAnyRole("ADMIN","ANALYST")

                        .requestMatchers(HttpMethod.POST, "/api/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/records/**")
                        .hasRole("ADMIN")

                        // Dashboard
                        .requestMatchers("/api/dashboard/**")
                        .hasAnyRole("ADMIN","ANALYST","VIEWER")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}