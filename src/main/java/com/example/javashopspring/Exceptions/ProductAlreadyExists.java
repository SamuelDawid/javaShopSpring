package com.example.javashopspring.Exceptions;

import com.example.javashopspring.domain.resources.Electronics;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExists extends JavaShopException {
    public ProductAlreadyExists(Electronics product) {
        super("Product already exists!" + product.toString(), HttpStatus.CONFLICT);
    }
}
