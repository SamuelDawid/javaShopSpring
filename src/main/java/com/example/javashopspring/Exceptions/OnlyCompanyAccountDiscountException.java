package com.example.javashopspring.Exceptions;

public class OnlyCompanyAccountDiscountException extends RuntimeException {
    public OnlyCompanyAccountDiscountException() {
        super("This discount is available only for Companies");
    }
}
