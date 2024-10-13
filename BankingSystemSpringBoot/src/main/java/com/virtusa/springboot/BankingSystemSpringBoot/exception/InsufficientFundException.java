package com.virtusa.springboot.BankingSystemSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientFundException extends RuntimeException {

    private Long accountNumber;

    public InsufficientFundException(Long accountNumber) {
        super(String.format("Transaction Denied as the account : %d lacks sufficient funds.", accountNumber));
        this.accountNumber = accountNumber;
    }

}
