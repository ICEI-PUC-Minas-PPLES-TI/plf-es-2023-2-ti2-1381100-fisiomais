package com.fisiomais.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("path", requestUri);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNoContentException(NotFoundException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("path", requestUri);
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(ServerException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("path", requestUri);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.put("message", "Erro interno do servidor. Tente novamente mais tarde.");
        response.put("exception", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenExpirationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleTokenExpirationException(TokenExpirationException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("path", requestUri);
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundExecption.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundExecption ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("path", requestUri);
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
