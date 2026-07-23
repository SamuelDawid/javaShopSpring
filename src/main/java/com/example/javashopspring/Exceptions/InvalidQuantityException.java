package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidQuantityException extends JavaShopException {
    public InvalidQuantityException() {

        super("Quantity must be a positive number!", HttpStatus.BAD_REQUEST);
    }
}
