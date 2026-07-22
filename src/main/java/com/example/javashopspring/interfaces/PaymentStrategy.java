package com.example.javashopspring.interfaces;

import java.math.BigDecimal;
//Strategy pattern
//Interface Segregation
@FunctionalInterface
public interface PaymentStrategy {
    void pay(BigDecimal amount, String customerId, String description);
}
