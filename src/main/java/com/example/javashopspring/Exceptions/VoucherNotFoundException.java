package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class VoucherNotFoundException extends JavaShopException {
    public VoucherNotFoundException() {
        super("Voucher was not found", HttpStatus.NOT_FOUND);
    }
}
