package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import java.time.LocalDate;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import lombok.Data;

@Data
public class ApplicationCustomerDto {

    private Long customerId;

    private String name;

    private AddressDto address;

    private LocalDate dateOfBirth;

    private String phoneNumber;
}
