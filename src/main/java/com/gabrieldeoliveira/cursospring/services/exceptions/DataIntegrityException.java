package com.gabrieldeoliveira.cursospring.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }
}
