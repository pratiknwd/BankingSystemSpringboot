package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthException extends RuntimeException{
    private String message;

    public AuthException(String message){
        super(message);
        this.message = message;
    }
}
