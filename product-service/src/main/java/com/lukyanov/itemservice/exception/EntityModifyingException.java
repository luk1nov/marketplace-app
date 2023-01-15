package com.lukyanov.itemservice.exception;

import lombok.Getter;

@Getter
public class EntityModifyingException extends RuntimeException{
    private final Integer errorCode = 40001;

    public EntityModifyingException(String message) {
        super(message);
    }
}
