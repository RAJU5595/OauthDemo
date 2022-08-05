package com.raju.demo.sample.exceptionhandler;

import com.raju.demo.sample.entity.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@PropertySource("classpath:messeges.properties")
public class GlobalExceptionHandler {

    @Value("${pathVariable_constraint}")
    private String pathVariableConstraintMsg;

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception exception){
        CustomErrorResponse error = new CustomErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(ConstraintViolationException exception){
        CustomErrorResponse error = new CustomErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(pathVariableConstraintMsg);
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
