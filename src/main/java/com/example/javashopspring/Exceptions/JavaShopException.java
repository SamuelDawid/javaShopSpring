package com.example.javashopspring.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class JavaShopException extends RuntimeException {
    private final HttpStatus status;

    public JavaShopException(String message, HttpStatus status) {
        super(message);
        this.status = status;

    }
}
