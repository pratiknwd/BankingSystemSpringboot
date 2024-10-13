package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import lombok.Data;

@Data
public class EditCustomerDetailsDto {

    private String name;

    private String email;

    private AddressDto address;

    private String gender;

    private String phoneNumber;
}
