package com.example.javashopspring.config;

import com.example.javashopspring.interfaces.PaymentStrategy;
import com.example.javashopspring.interfaces.Validator;
import com.example.javashopspring.repo.AccountsRepository;
import com.example.javashopspring.service.PaymentService;
import com.example.javashopspring.validators.AmountValidator;
import com.example.javashopspring.validators.NotBlockedValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class ShopConfig {
    @Bean public PaymentService paymentService(AccountsRepository accountRepository){
        Map<String, PaymentStrategy> methods = Map.of(
                "KARTA",   (amount, customerId, desc) -> System.out.println("Card charged: " + amount),
                "BLIK",    (amount, customerId, desc) -> System.out.println("BLIK paid: " + amount),
                "PRZELEW", (amount, customerId, desc) -> System.out.println("Transfer sent: " + amount)
        );
        List<Validator> validators = List.of(
                new AmountValidator(),
                new NotBlockedValidator(accountRepository)
        );
        return new PaymentService(methods,validators);
    }

}
