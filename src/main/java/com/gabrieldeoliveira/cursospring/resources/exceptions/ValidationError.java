package com.gabrieldeoliveira.cursospring.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, Long timestamp, String message) {
        super(status, timestamp, message);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }
}
