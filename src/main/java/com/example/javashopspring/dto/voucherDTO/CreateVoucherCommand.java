package com.example.javashopspring.dto.voucherDTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateVoucherCommand(
        @NotBlank
        String voucherName,
        @NotNull
        @Future(message = "expiration date must be ahead ")
        LocalDate expirationDate,
        @Positive(message = "percentage must be positive")
        @Max(25)
        int percentage) {
}

