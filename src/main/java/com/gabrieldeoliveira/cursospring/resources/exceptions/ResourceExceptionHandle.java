package com.gabrieldeoliveira.cursospring.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFountException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandle {
    
    @ExceptionHandler(ObjectNotFountException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFountException e, HttpServletRequest req) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
            status.value(),
            System.currentTimeMillis(),
            e.getMessage());
        
        return ResponseEntity.status(status).body(err);
    }
}
