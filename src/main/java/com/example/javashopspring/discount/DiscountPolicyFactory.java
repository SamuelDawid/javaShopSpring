package com.example.javashopspring.discount;

import lombok.RequiredArgsConstructor;
import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.enums.AccountType;
import com.example.javashopspring.interfaces.DiscountPolicy;
import com.example.javashopspring.service.DiscountService;

import java.util.Map;
//Factory
@RequiredArgsConstructor
public class DiscountPolicyFactory {
    private final Map<AccountType, DiscountPolicy> policies;

    public static DiscountPolicyFactory create(DiscountService service) {
        return new DiscountPolicyFactory(Map.of(
                AccountType.COMPANY, new CompanyDiscountPolicy(service),
                AccountType.NORMAL,  new VoucherDiscountPolicy(service)
        ));
    }

    public DiscountPolicy forAccount(Account account) {
        return policies.get(account.getType());
    }
}
