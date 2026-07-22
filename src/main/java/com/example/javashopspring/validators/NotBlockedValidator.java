package com.example.javashopspring.validators;

import lombok.RequiredArgsConstructor;
import com.example.javashopspring.domain.User.Account;
import com.example.javashopspring.interfaces.Validator;
import com.example.javashopspring.repo.AccountsRepository;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class NotBlockedValidator implements Validator {
    private final AccountsRepository accountsRepository;
    @Override
    public Optional<String> validate(BigDecimal amount, String customerId) {
        Optional<Account> accountToValidate = accountsRepository.findAccount(customerId);
        if(accountToValidate.get().isBlocked()) return Optional.of("Account is blocked");
        return Optional.empty();
    }
}
