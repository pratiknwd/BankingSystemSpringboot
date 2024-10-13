package com.virtusa.springboot.BankingSystemSpringBoot.dto.Account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBalanceDto {

    @NotNull(message = "accountNUmber cannot be empty")
    private Long accountNumber;

    @Min(value = 500, message = "minimum 500 required for deposit")
    private Double amount;

    @NotBlank(message = "accountNUmber cannot be empty")
    private String transactionId;
}
