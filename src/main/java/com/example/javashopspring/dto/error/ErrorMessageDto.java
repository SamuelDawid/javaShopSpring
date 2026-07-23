package com.example.javashopspring.dto.error;

import java.time.LocalDateTime;

public record ErrorMessageDto(String message,
                              int statusCode,
                              LocalDateTime time) {
}
