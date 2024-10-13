package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import java.time.LocalDate;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.auth.UserDTO;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import lombok.Data;

@Data
public class CustomerDto {

    private Long customerId;

    private UserDTO user;

    private String name;

    private String gender;

    private AddressDto address;

    private LocalDate dateOfBirth;

    private String phoneNumber;
}
