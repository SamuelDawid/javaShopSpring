package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends JavaShopException {
    public ProductNotFoundException(String productID) {
        super("Product not found with ID: "+ productID, HttpStatus.NOT_FOUND);
    }
}
