package com.lukyanov.itemservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public void handleEntityNotFoundException(EntityNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(EntityModifyingException.class)
    public ResponseEntity<ExceptionResponse> handleEntityModifyingException(EntityModifyingException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(e.getErrorCode(), e.getMessage()));
    }
}
