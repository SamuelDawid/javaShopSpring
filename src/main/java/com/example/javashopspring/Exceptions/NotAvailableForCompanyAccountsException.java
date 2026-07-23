package com.example.javashopspring.Exceptions;

import org.springframework.http.HttpStatus;

public class NotAvailableForCompanyAccountsException extends JavaShopException {
    public NotAvailableForCompanyAccountsException() {
        super("This option is not available for Company type account", HttpStatus.BAD_REQUEST);
    }
}
