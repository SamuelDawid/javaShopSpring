package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class VoucherAlreadyExistsException extends JavaShopException {
    public VoucherAlreadyExistsException() {
        super("Voucher Already Exists", HttpStatus.CONFLICT);
    }
}
