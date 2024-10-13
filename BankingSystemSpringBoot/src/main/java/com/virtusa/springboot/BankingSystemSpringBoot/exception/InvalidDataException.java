package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidDataException extends RuntimeException {

    private String message;

    public InvalidDataException(String message) {
        super(message);
        this.message = message;
    }

}
