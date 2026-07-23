package com.example.javashopspring.dto.error;

import java.time.LocalDate;

public record ErrorMessageDto(String message,
                              int statusCode,
                              LocalDate time) {
}
