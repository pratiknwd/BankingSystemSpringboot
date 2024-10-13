package com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateTransactionDto {

    @NotNull(message = "account Number Cannot be empty")
    private Long accountNumber;

    @NotNull(message = "Beneficiary account Number Cannot be empty")
    private Long beneficiaryAccountNumber;

    private String transactionType;

    private String transactionChannel;

    @Min(value = 100, message = "minimum transferable amount is 100")
    private Double amount;

    private String remarks;

    @JsonProperty("tPin")
    @NotNull(message = "TPIN required to complete the Transaction")
    private Integer tPin;

}
