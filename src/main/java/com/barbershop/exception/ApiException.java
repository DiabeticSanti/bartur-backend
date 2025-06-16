package com.barbershop.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final CustomErrorResponse error;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.error = new CustomErrorResponse(message, status);
    }

    public CustomErrorResponse getError() {
        return error;
    }
}
