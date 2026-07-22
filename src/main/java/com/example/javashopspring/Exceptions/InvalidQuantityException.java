package com.example.javashopspring.Exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {

        super("Quantity must be a positive number!");
    }
}
