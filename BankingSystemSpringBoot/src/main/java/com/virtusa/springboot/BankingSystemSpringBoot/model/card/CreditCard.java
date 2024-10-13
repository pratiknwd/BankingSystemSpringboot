package com.virtusa.springboot.BankingSystemSpringBoot.model.card;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "CreditCards")
public class CreditCard extends Card {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "credit_limit")
    private Double creditLimit;
}

