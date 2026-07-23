package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class NotEnoughPointsException extends JavaShopException {
    public NotEnoughPointsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
