package com.learning.eCommerce.exception.customerException;

public class InvalidCredential extends RuntimeException{

    public  InvalidCredential(String message){
        super(message);
    }
}
