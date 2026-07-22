package com.example.javashopspring.Exceptions;

public class InvalidVoucherException extends RuntimeException {
    public InvalidVoucherException() {
        super("Invalid or expired voucher");
    }
}
