package com.virtusa.springboot.BankingSystemSpringBoot.dto.Account;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAccountDto {

    private Long customerId;

    @NotNull(message = "branchId cannot be blank")
    private Long branchId;

    @Pattern(regexp = "Savings|Investment|Current", message = "Account-Type must be one of Savings,InvestMent or Current.")
    private String accountType;

    @Min(value = 2000,message = "minimun of 2000 is needed to open an account")
    private Double balance;

    @NotNull(message = "docId cannot be null")

    List<Long> documentId;

}
