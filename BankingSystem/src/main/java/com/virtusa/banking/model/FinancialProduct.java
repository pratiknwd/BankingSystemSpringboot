package com.virtusa.banking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class FinancialProduct {
	@Id
	@GeneratedValue
	private int id;
	private String productName;
	private double interestRate;
	private double maturityPeriodInYears;
	private double minInvestment;
	private double maxInvestment;
	
	@ManyToMany(mappedBy = "financialProductsOffered")
    private List<Bank> banks = new ArrayList<>();
	
	@ManyToOne
	 @JoinColumn(name = "account_number")
	private Account account;
	
	public FinancialProduct() {
		this.banks = new ArrayList<>();
	}

	public FinancialProduct( String productName, double interestRate, double maturityPeriodInYears,
			double minInvestment, double maxInvestment) {
		super();
		this.productName = productName;
		this.interestRate = interestRate;
		this.maturityPeriodInYears = maturityPeriodInYears;
		this.minInvestment = minInvestment;
		this.maxInvestment = maxInvestment;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getMaturityPeriodInYears() {
		return maturityPeriodInYears;
	}

	public void setMaturityPeriodInYears(double maturityPeriodInYears) {
		this.maturityPeriodInYears = maturityPeriodInYears;
	}

	public double getMinInvestment() {
		return minInvestment;
	}

	public void setMinInvestment(double minInvestment) {
		this.minInvestment = minInvestment;
	}

	public double getMaxInvestment() {
		return maxInvestment;
	}

	public void setMaxInvestment(double maxInvestment) {
		this.maxInvestment = maxInvestment;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}



	

}
