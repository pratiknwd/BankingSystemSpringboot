package com.virtusa.springboot.BankingSystemSpringBoot.dto.application;

import lombok.Data;

@Data
public class PendingAccountApplicationDto extends PendingApplicationDto {
    
    private String branchIfsc;
}
