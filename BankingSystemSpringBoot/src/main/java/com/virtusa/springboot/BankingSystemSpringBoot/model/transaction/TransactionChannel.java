package com.virtusa.springboot.BankingSystemSpringBoot.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Transaction_Channels")
public class TransactionChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_channel_id")
    private Long transactionChannelId;

    @Column(name = "transaction_channel")
    private String transactionChannel;
}
