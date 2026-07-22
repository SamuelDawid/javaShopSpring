package com.example.javashopspring.Exceptions;

public class VoucherAlreadyExistsException extends RuntimeException {
    public VoucherAlreadyExistsException() {
        super("Voucher Already Exists");
    }
}
