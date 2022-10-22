/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.exceptions;

import com.example.elibrary.responses.ErrResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author user
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override()
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        /**
         * Errors
         */
        List<String> errors = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    
        ErrResponse response = new ErrResponse("Validation Error", errors);
        
        //
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse("Access denied");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalHander(Exception ex, WebRequest request) {
        ErrResponse response = new ErrResponse(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }
}
