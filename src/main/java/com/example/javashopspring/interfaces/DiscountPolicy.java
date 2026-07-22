package com.example.javashopspring.interfaces;
// Open/Closed:
import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.models.Cart;

import java.math.BigDecimal;
// Strategy pattern
public interface DiscountPolicy {
    BigDecimal apply(Cart cart, Account account);
}
