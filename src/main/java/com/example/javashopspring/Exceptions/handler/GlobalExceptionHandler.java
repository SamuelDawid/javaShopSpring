package com.example.javashopspring.Exceptions.handler;

import com.example.javashopspring.Exceptions.JavaShopException;
import com.example.javashopspring.dto.error.ErrorMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(JavaShopException.class)
    public ResponseEntity<ErrorMessageDto> handleException(JavaShopException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorMessageDto(exception.getMessage(), exception.getStatus().value(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleUnexpected(Exception e) {
        logger.error("Unexpected error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageDto("Unexpected error", 500, LocalDateTime.now()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgument(MethodArgumentNotValidException e){
        StringBuilder errorFields = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()){
            errorFields.append(error.getField()).append(": ").append(error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(errorFields.toString(),e.getStatusCode().value(),LocalDateTime.now()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageDto> handleMessageNotReadable(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(e.getMessage(),400,LocalDateTime.now()));
    }
}
