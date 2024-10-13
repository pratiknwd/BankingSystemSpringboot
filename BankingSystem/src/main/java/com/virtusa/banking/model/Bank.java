package com.virtusa.banking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_id", nullable = false, unique = true)
	private long bankId;
	@Column(name = "bank_name", nullable=false, unique=true)
	private String bankName;

	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch=FetchType.EAGER)	// to fetch branches with bank data
	List<Branch> branches;
	
	@ManyToMany
	@JoinTable(
	        name = "bank_financial_products",
	        joinColumns = @JoinColumn(name = "bank_id"),
	        inverseJoinColumns = @JoinColumn(name = "financial_product_id")
	    )
	private List<FinancialProduct> financialProductsOffered;

	public Bank() {
		this.financialProductsOffered = new ArrayList<>();
	}

	public Bank(int bankId, String bankName) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.branches = new ArrayList<>();
		this.financialProductsOffered = new ArrayList<>();
	}

	public long getBankId() {
		return bankId;
	}

	// Only READ operation allowed for Bank Name
	public String getBankName() {
		return bankName;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}	

	public List<FinancialProduct> getFinancialProductsOffered() {
		return this.financialProductsOffered;
	}

	public void setFinancialProductsOffered(List<FinancialProduct> financialProductsOffered) {
		this.financialProductsOffered = financialProductsOffered;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "Bank{" + "bankId=" + bankId + ", name=' " + bankName + '\'' + '}';
	}

}

