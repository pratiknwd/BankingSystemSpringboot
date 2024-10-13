package com.virtusa.springboot.BankingSystemSpringBoot.dto.Loan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeDto {

    @NotNull
    private Float loanInterest;

    @NotBlank
    @Pattern(regexp = "Personal|Vehicle|Home|Education", message = "Loan Type must be one of Personal, Vehicle, Home or Education.")
    private String loanType;
}
