package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class UnavailableProducts extends JavaShopException {
    public UnavailableProducts(String product) {

        super("Unavailable Product: " + product, HttpStatus.CONFLICT);
    }
}
