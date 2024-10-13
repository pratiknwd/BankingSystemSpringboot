package com.virtusa.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "branch")
public class Branch {
	@Id
	@Column(name = "ifsc", nullable = false)
	private String ifsc;

	@NotNull
	@Column(name = "address", nullable = false)
	private String address;

	@ManyToOne
	@JoinColumn(name = "bank_id", nullable = false)
	private Bank bank;

	public Branch() {
	}

	public Branch(String ifsc, String address) {
		this.ifsc = ifsc;
		this.address = address;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "{" + " \"ifsc\":" + "\"" + ifsc + "\"" + ", \"address\":" + "\""+ address + "\"" +  "}";
	}

}