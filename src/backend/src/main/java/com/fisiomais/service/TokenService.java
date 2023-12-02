package com.fisiomais.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fisiomais.exception.TokenExpirationException;
import com.fisiomais.model.User;

import java.util.Date;
import java.time.Instant;

@Service
public class TokenService {
    private final String secretKey = "fisiomais_secret_jwt";
    private static final Logger logger = LogManager.getLogger(TokenService.class);

    public String gerarToken(User usuario) {
        return JWT.create()
                .withIssuer("User")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withClaim("email", usuario.getEmail())
                // .withExpiresAt(Date.from(Instant.now().plusSeconds(5 * 60)))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer("User")
                .build()
                .verify(token)
                .getSubject();
    }
}
