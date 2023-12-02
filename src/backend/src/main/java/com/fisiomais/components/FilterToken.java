package com.fisiomais.components;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fisiomais.controller.AuthController;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.User;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;
import com.fisiomais.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    public FilterToken(TokenService tokenService,
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository) {
        this.tokenService = tokenService;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException, BusinessException {
        String token = null;
        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.replace("Bearer ", "");

            if (token != null) {
                String subject = this.tokenService.getSubject(token);

                if (subject != null) {
                    User user = this.pacienteRepository.findByEmail(subject);

                    if (user == null) {
                        user = this.fisioterapeutaRepository.findByEmail(subject);
                    }

                    if (user != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user,
                                null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        logger.warn("User not found for subject: {}", subject);
                    }
                } else {
                    logger.warn("Subject not found in the token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
