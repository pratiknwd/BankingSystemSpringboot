package com.virtusa.springboot.BankingSystemSpringBoot.dto.Account;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.branch.BranchDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.CustomerDto;

import lombok.Data;

@Data
public class AccountDetailsDTO {

    private Long accountId;

    private CustomerDto customer;

    private BranchDto branch;

    private String accountType;

    private Long accountNumber;

    private Double balance;

    private String accountStatus;

    private Integer tpin;
}
