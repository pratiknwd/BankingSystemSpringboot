package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenException extends RuntimeException{
    private String message;

    public AuthTokenException(String message){
        super(message);
        this.message=message;
    }
    
}
