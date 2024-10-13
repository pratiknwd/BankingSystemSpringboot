package com.virtusa.springboot.BankingSystemSpringBoot.dto.application;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.ApplicationCustomerDto;

import lombok.Data;

@Data
public class PendingApplicationDto {

    private Long applicationId;

    private ApplicationCustomerDto customer;

    private String applicationType;

    private Long applicationNumber;

    private String dateOfApplication;

    private String applicationStatus;
}
