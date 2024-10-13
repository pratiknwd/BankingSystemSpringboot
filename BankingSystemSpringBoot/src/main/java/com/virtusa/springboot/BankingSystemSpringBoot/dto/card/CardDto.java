package com.virtusa.springboot.BankingSystemSpringBoot.dto.card;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CardDto {

    private Long cardNumber;

    private Integer cvv;

    private Integer cardPin;

    private LocalDate expiryDate;

    private LocalDate issueDate;

    private String cardType;

}
