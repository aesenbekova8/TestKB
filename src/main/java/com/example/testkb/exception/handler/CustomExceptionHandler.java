package com.example.testkb.exception.handler;

import com.example.testkb.exception.CustomEntityNotFoundException;
import com.example.testkb.exception.ExceptionResponse;
import com.example.testkb.exception.LogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleLogicExceptions(LogicException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(CustomEntityNotFoundException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(
                new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
