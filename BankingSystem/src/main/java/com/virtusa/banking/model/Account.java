package com.virtusa.banking.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account {	
	@Id
	@Column(name = "account_number", nullable = false, unique = true)
	private long accountNumber;
	@Enumerated(EnumType.STRING)
	@Column(name = "account_type", nullable = false, unique = false)
	private AccountType accountType; 
	@Column(name = "ifsc", nullable = false, unique = false)
	private String ifsc;
	@Column(name = "balance")
	private double balance = 0.0; 
	@Column(name = "access", nullable = false, unique = false)
	private boolean access = true; 												
	@Column(name = "overdraft_limit", nullable = false, unique = false)
	private double overdraftLimit;
	@Column(name = "interest_rate", nullable = true)
	private double interestRate;
	@Column(name = "withdraw_limit", nullable = true)
	private double withdrawLimit;
	@Enumerated(EnumType.STRING)
	@Column(name = "investment_type", nullable = true)
	private InvestmentType investmentType;
	@Column(name = "opted_for_recurring_transaction", nullable = false, unique = false)
	private boolean optedForRecurringTransaction;

	@OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
	private List<CardDetails> cardDetails=new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 																
	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch; 
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Transaction> transactions;
    
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private List<FinancialProduct> financialProducts = new ArrayList<>();

	@PrePersist
	@PreUpdate
	private void syncIfsc() {
		if (branch != null) {
			this.ifsc = branch.getIfsc();
		}
	}
	public Account() {
		this.transactions = new HashSet<>();
		this.financialProducts = new ArrayList<>();
	}
	
	public Account(long accountNumber, AccountType accountType, String ifsc) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.ifsc = ifsc;
		
	}

	public void deposit(double amount) {
		if (amount > 0) {
			this.balance += amount; 
		}
	}

	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= this.balance) {
			this.balance -= amount; 
			return true;
		}
		return false;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<FinancialProduct> getFinancialProducts() {
		return financialProducts;
	}

	public void setFinancialProducts(List<FinancialProduct> financialProducts) {
		this.financialProducts = financialProducts;
	}

	public boolean isOptedForRecurringTransaction() {
		return optedForRecurringTransaction;
	}

	public void setOptedForRecurringTransaction(boolean optedForRecurringTransaction) {
		this.optedForRecurringTransaction = optedForRecurringTransaction;

	}
	@Override
	public String toString() {
		BigDecimal bigDecimal = BigDecimal.valueOf(balance);
		return "Account [accountNumber=" + accountNumber + 
				", accountType=" + accountType +
				", ifsc=" + ifsc
				+ ", balance=" + bigDecimal.toPlainString() 
				+ "]";
	}
	
	public List<CardDetails> getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(List<CardDetails> cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	
	
}
