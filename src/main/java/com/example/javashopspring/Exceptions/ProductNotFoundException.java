package com.example.javashopspring.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productID) {
        super("Product not found with ID: "+ productID);
    }
}
