package com.fisiomais.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fisiomais.components.ExceptionHandlingFilter;
import com.fisiomais.components.FilterToken;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final FilterToken filter;
    private final ExceptionHandlingFilter exceptionHandlingFilter;

    public SecurityConfig(FilterToken filter,
            ExceptionHandlingFilter exceptionHandlingFilter) {
        this.filter = filter;
        this.exceptionHandlingFilter = exceptionHandlingFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/api/auth").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/fisioterapeuta").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/paciente").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/api/auth/credentials/**").authenticated() // Allow OPTIONS for /credentials
                .requestMatchers(HttpMethod.GET, "/api/auth/credentials/**").authenticated() // Require authentication for GET /credentials
                .anyRequest().authenticated()
                .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(exceptionHandlingFilter)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
