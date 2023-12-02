package com.fisiomais.exception;

public class TokenExpirationException extends RuntimeException {
    public TokenExpirationException(String message, Throwable cause) {
        super(message, cause);
    }
}

