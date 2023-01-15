package com.lukyanov.itemservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ExceptionResponse implements Serializable {

    private int errorCode;
    private String message;
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public ExceptionResponse(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
