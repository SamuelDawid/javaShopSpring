package com.example.javashopspring.Exceptions;

public class NotEnoughPointsException extends RuntimeException {
    public NotEnoughPointsException(String message) {
        super(message);
    }
}
