package com.example.demo.exception;

import org.springframework.http.HttpStatus;


public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
