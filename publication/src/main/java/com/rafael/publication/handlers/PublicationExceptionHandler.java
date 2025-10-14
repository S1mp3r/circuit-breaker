package com.rafael.publication.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rafael.publication.domains.ErroResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class PublicationExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerExceptionsValidate(
        Exception exception, 
        HttpServletRequest request
    ) {
        ErroResponse erroResponse = ErroResponse
            .builder()
            .timeStamp(System.currentTimeMillis())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(exception.getMessage())
            .path(request.getRequestURI())
            .build();
        
        log.error("[Error] payload={}", erroResponse, exception);

        return ResponseEntity.internalServerError().body(erroResponse);
    }

}
