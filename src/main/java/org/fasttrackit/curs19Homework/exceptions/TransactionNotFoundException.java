package org.fasttrackit.curs19Homework.exceptions;


public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String message){
        super(message);
    }
}
