package com.example.javashopspring.Exceptions;

public class VoucherNotFoundException extends RuntimeException {
    public VoucherNotFoundException() {
        super("Voucher was not found");
    }
}
