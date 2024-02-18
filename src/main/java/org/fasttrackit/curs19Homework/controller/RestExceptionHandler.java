package org.fasttrackit.curs19Homework.controller;

import lombok.Builder;
import org.fasttrackit.curs19Homework.exceptions.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTransactionNotFoundException(TransactionNotFoundException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @Builder
    record ErrorResponse(String message){}
}


