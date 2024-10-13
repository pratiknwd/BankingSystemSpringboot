package com.virtusa.springboot.BankingSystemSpringBoot.model.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "Debit_Card_Types")
public class DebitCardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debit_card_type_id")
    private Long debitCardId;

    @Column(name = "debit_card_type")
    @NotNull(message = "Please enter Debit Card Type")
    private String debitCardType;

    @Column(name = "daily_limit")
    @NotNull(message = "Please enter daily limit")
    private Double dailyLimit;

    @Column(name = "weekly_limit")
    @NotNull(message = "Please enter weekly limit")
    private Double weeklyLimit;

    @Column(name = "monthly_limit")
    @NotNull(message = "Please enter monthly limit")
    private Double monthlyLimit;
}
