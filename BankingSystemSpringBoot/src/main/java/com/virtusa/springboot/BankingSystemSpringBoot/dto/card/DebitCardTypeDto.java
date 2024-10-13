package com.virtusa.springboot.BankingSystemSpringBoot.dto.card;

import lombok.Data;

@Data
public class DebitCardTypeDto {

    private String debitCardType;

    private Double dailyLimit;

    private Double weeklyLimit;

    private Double monthlyLimit;

}
