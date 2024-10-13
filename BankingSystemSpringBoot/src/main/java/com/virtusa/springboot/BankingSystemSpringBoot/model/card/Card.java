package com.virtusa.springboot.BankingSystemSpringBoot.model.card;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "card_type")
    @Pattern(regexp = "Credit|Debit", message = "Card Type must be one of Credit or Debit.")
    private String cardType;

    @Column(name = "card_pin")
    private Integer cardPin;

    private Integer cvv;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "isuue_date")
    private LocalDate issueDate;
}
