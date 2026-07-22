package com.example.javashopspring.interfaces;

import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.enums.AccountType;
import com.example.javashopspring.models.Voucher;

import java.math.BigDecimal;
//Interface Segregation
public interface DiscountCalculations {
    BigDecimal applyCompany(BigDecimal basePrice, AccountType type);
    BigDecimal applyVoucher(BigDecimal basePrice, Voucher voucher);
    Voucher exchangePoints(Account account, int points);
}
