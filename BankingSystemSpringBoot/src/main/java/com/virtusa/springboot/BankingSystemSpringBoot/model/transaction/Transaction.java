package com.virtusa.springboot.BankingSystemSpringBoot.model.transaction;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "Transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "accociated_account_number")
	private Long accociatedAccountNumber;

	@Column(name = "transfer_desciption")
	private String transferDesciption;

	// @OneToOne
	@ManyToOne
	@JoinColumn(name = "transaction_type_id")
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private TransactionType transactionType;

	// @OneToOne
	@ManyToOne
	@JoinColumn(name = "transaction_channel_id")
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private TransactionChannel transactionChannel;

	private Double amount;

	private String remarks;

	@Column(name = "trasaction_date")
	private LocalDate trasactionDate;
	
}
