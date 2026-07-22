package com.example.javashopspring.discount;

import lombok.RequiredArgsConstructor;
import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.interfaces.DiscountPolicy;
import com.example.javashopspring.models.Cart;
import com.example.javashopspring.service.DiscountService;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CompanyDiscountPolicy implements DiscountPolicy {
    private final DiscountService discountService;

    @Override
    public BigDecimal apply(Cart cart, Account account) {
        return discountService.applyCompany(cart.getCartTotal(), account.getType());
    }
}
