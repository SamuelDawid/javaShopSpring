package com.example.javashopspring.dto.productDTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotBlank
        String id,
        @NotBlank
        String name,
        @DecimalMin("0.01")
        @NotNull
        BigDecimal price,
        @PositiveOrZero(message = "quantity must be zero or positive")
        int quantity
) {
}
