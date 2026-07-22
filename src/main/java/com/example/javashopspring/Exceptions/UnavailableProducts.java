package com.example.javashopspring.Exceptions;

public class UnavailableProducts extends RuntimeException {
    public UnavailableProducts(String product) {

        super("Unavailable Product: " + product);
    }
}
