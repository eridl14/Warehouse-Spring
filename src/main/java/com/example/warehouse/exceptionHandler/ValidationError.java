package com.example.warehouse.exceptionHandler;

import lombok.Data;

@Data

public class ValidationError {
    private String field;
    private String message;
    private Object rejectedValue;
}