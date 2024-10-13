package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Login {

	@NotNull(message = "*userId cannot be blank")
	private Integer userId;
	@NotNull(message = "*select a bank")
	private String bank;
	@NotEmpty(message = "*password cannot be blank")
	private String password;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(Integer userId, String bank, String password) {
		super();
		this.userId = userId;
		this.bank = bank;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", bank=" + bank + ", password=" + password + "]";
	}

}
