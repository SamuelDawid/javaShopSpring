package com.example.javashopspring.Exceptions;

import com.example.javashopspring.domain.resources.Electronics;

public class ProductAlreadyExists extends RuntimeException {
    public ProductAlreadyExists(Electronics product) {
        super("Product already exists!" + product.toString());
    }
}
