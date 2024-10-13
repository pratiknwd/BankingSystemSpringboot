package com.virtusa.banking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Column(name = "account_number", nullable = false, unique = false)
	private long accountNumber;
	@Column(name = "ref_account_number", nullable = false, unique = false)
	private long refAccountNumber;
	@Column(name = "amount", nullable = false, unique = false)
	private double amount;
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type", nullable = false, unique = false)
	private TransactionType transactionType;
	@Enumerated(EnumType.STRING)
	@Column(name = "mode_of_transaction", nullable = false, unique = false)
	private ModeOfTransaction modeOfTransaction;
	@Temporal(TemporalType.TIMESTAMP) // it gives both time and date
	@Column(name = "date_time_of_transaction")
	private Date dateTime;
	@Column(name = "balance_remaining", nullable = false, unique = false)
	private double balanceRemaining;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	public Transaction() {

	}

	public Transaction(long accountNumber, long refAccountNumber, double amount, TransactionType transactionType,
			ModeOfTransaction modeOfTransaction, Date dateTime) {

		this.accountNumber = accountNumber;
		this.refAccountNumber = refAccountNumber;
		this.amount = amount;
		this.transactionType = transactionType;
		this.modeOfTransaction = modeOfTransaction;
		this.dateTime = dateTime;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getBalanceRemaining() {
		return balanceRemaining;
	}

	public void setBalanceRemaining(double balanceRemaining) {
		this.balanceRemaining = balanceRemaining;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getRefAccountNumber() {
		return refAccountNumber;
	}

	public void setRefAccountNumber(long refAccountNumber) {
		this.refAccountNumber = refAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public ModeOfTransaction getModeOfTransaction() {
		return modeOfTransaction;
	}

	public void setModeOfTransaction(ModeOfTransaction modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	// this toString() method does not contains the account details, add ["account"
	// + account] in the end
	// if needed
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNumber=" + accountNumber
				+ ", refAccountNumber=" + refAccountNumber + ", amount=" + amount + ", transactionType="
				+ transactionType + ", modeOfTransaction=" + modeOfTransaction + ", dateTime=" + dateTime
				+ ", balanceRemaining=" + balanceRemaining + "]";
	}

}
