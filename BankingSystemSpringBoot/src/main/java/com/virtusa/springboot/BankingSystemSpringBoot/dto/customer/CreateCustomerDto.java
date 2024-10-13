package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import java.time.LocalDate;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerDto {

    @NotBlank(message = "name cannot be empty")
    private String name;

    private AddressDto addressDto;

    private String gender;

    @NotNull(message = "Date of Birth is mandatory")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 13, message = "invalid phoneNum")
    private String phoneNumber;
}
