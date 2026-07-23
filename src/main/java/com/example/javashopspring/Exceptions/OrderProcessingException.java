package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class OrderProcessingException extends JavaShopException {
    public OrderProcessingException() {
        super("Nothing could be shipped - all products out of stock", HttpStatus.BAD_REQUEST);
    }
}
