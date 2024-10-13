package com.virtusa.springboot.BankingSystemSpringBoot.dto.application;

import lombok.Data;

@Data
public class ApplicationDetailsDto {
    private Long applicationId;

    private String applicationType;

    private Long applicationNumber;

    private String dateOfApplication;

    private String ifsc;

    private String applicationStatus;
}
