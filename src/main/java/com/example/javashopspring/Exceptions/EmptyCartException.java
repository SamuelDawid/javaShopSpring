package com.example.javashopspring.Exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cart is empty, can not checkout");
    }
}
