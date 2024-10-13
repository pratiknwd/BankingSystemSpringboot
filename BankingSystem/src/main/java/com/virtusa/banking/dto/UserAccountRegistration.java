package com.virtusa.banking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserAccountRegistration {

	private Integer userId;
//	@NotBlank(message = "*Name cannnot be blank")
	private String name;

//	@NotBlank(message = "*Email can't be blank")
	private String email;
//	@Digits(integer = 10, fraction = 0, message = "*Invalid Phone Number")
	private Long mobile;

	private String address;

	@NotBlank(message = "*Please select a bank")
	private String bank;
	@NotBlank(message = "*Please select a ifsc")
	private String ifsc;
	@NotBlank(message = "*Please select a account-type")
	private String accountType;

	private String uIdType;

	private Long uId;

	@NotNull(message = "*Amount can't be blank")
	@Min(value = 2000, message = "*A minimum sum of 2000 is needed to open an account")
	private Double initialAmount;

	public UserAccountRegistration() {
		super();
	}

	public UserAccountRegistration(Integer userId, String name, String email, Long mobile, String address, String bank,
			String ifsc, String accountType, Double initialAmount, String uIdType, Long uId) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.bank = bank;
		this.ifsc = ifsc;
		this.accountType = accountType;
		this.initialAmount = initialAmount;
		this.uIdType = uIdType;
		this.uId = uId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getuIdType() {
		return uIdType;
	}

	public void setuIdType(String uIdType) {
		this.uIdType = uIdType;
	}

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public Double getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(Double initialAmount) {
		this.initialAmount = initialAmount;
	}

	@Override
	public String toString() {
		return "UserAccountRegistration [userId=" + userId + ", name=" + name + ", email=" + email + ", mobile="
				+ mobile + ", address=" + address + ", bank=" + bank + ", ifsc=" + ifsc + ", accountType=" + accountType
				+ ", uIdType=" + uIdType + ", uId=" + uId + ", initialAmount=" + initialAmount + "]";
	}

}
