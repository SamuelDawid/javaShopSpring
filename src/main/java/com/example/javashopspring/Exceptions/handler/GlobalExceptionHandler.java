package com.example.javashopspring.Exceptions.handler;

import com.example.javashopspring.Exceptions.JavaShopException;
import com.example.javashopspring.dto.error.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JavaShopException.class)
    public ResponseEntity<ErrorMessageDto>  handleException(JavaShopException exception){
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorMessageDto(exception.getMessage(),exception.getStatus().value(), LocalDate.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleUnexpected(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageDto("Unexpected error", 500,LocalDate.now()));
    }
}
