package com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TransactionDto {

    private Long transactionId;

    private Long account;

    private Long accociatedAccountNumber;

    private String transferDesciption;

    private String transactionType;

    private String transactionChannel;

    private Double amount;

    private String remarks;

    private LocalDate trasactionDate;

}
