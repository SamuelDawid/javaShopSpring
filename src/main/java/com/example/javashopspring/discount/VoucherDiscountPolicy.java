package com.example.javashopspring.discount;

import lombok.RequiredArgsConstructor;
import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.interfaces.DiscountPolicy;
import com.example.javashopspring.models.Cart;
import com.example.javashopspring.models.Voucher;
import com.example.javashopspring.service.DiscountService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

@RequiredArgsConstructor
public class VoucherDiscountPolicy implements DiscountPolicy {
    private final DiscountService discountService;

    @Override
    public BigDecimal apply(Cart cart, Account account) {
        Optional<Voucher> biggest = account.getVouchersList().stream()
                .max(Comparator.comparingInt(Voucher::getPercentage));
        if (biggest.isEmpty()) {
            return cart.getCartTotal(); // nothing to apply
        }
        Voucher voucher = biggest.get();
        account.removeVoucherFromAccount(voucher);
        return discountService.applyVoucher(cart.getCartTotal(), voucher);
    }
}
