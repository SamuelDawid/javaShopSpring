package com.example.javashopspring.Exceptions;

public class NoSuchDiscountException extends RuntimeException {
    public NoSuchDiscountException(String message) {
        super(message);
    }
}
