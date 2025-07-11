package com.example.spring_boot_simple_company_api.record;

import org.springframework.validation.FieldError;

public record RestErrorMessage (String field, String message) {
    public RestErrorMessage(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}