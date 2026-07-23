package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchDiscountException extends JavaShopException {
    public NoSuchDiscountException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
