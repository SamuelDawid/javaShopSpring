package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class OnlyCompanyAccountDiscountException extends JavaShopException {
    public OnlyCompanyAccountDiscountException() {
        super("This discount is available only for Companies", HttpStatus.BAD_REQUEST);
    }
}
