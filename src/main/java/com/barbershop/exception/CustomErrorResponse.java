package com.barbershop.exception;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse{

    private String code;

    private String message;

    private int state;

    private Object responseObject;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(String message, HttpStatus state) {
        this.message = message;
        this.state = state.value();
        this.code = state.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

}
