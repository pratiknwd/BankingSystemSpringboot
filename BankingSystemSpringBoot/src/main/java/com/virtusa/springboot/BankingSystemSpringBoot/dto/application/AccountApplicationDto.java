package com.virtusa.springboot.BankingSystemSpringBoot.dto.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountApplicationDto {

    @NotNull(message = "customerId cannot be empty")
    private Long customerId;

    @NotBlank(message = "applicationType Cannot be Empty")
    private String applicationType;

    @NotBlank(message = "please enter a ifsc")
    private String ifsc;
}
