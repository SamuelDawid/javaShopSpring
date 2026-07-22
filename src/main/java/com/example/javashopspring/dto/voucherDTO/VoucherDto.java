package com.example.javashopspring.dto.voucherDTO;

import java.time.LocalDate;

public record VoucherDto(long id, String voucherName,
                         LocalDate expirationDate,
                         int percentage,
                         boolean used
) {
}
