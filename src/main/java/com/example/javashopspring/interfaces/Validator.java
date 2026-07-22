package com.example.javashopspring.interfaces;

import java.math.BigDecimal;
import java.util.Optional;
// Open/Closed
public interface Validator {
    Optional<String> validate(BigDecimal amount, String customerId);
}
