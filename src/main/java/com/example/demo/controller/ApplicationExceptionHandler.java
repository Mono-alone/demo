package com.example.demo.controller;

import com.example.demo.exception.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    protected ResponseEntity<Object> handleConflict(ApplicationException e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(), e.getHttpStatus(), new Timestamp(System.currentTimeMillis()));
        return this.handleExceptionInternal(e, response, new HttpHeaders(), e.getHttpStatus(), request);
    }
}
