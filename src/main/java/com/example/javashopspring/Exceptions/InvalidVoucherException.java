package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidVoucherException extends JavaShopException {
    public InvalidVoucherException() {
        super("Invalid or expired voucher", HttpStatus.BAD_REQUEST);
    }
}
