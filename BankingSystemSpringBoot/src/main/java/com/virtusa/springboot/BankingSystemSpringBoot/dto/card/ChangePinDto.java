package com.virtusa.springboot.BankingSystemSpringBoot.dto.card;

import lombok.Data;

@Data
public class ChangePinDto {
    private Long number;

    private Integer newPin;

    private String password;
}
