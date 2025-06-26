package com.example.warehouse.exceptionHandler;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

public class ValidationErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private List<ValidationError> errors = new ArrayList<>();

    public void addError(ValidationError error) {
        this.errors.add(error);
    }
}