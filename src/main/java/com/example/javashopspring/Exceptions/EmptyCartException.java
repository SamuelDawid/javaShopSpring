package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class EmptyCartException extends JavaShopException {
    public EmptyCartException() {
        super("Cart is empty, can not checkout", HttpStatus.BAD_REQUEST);
    }
}
