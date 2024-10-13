package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreditCardApplicationDto {

	@NotEmpty(message = "*name cannot be Empty")
	private String name;
	@NotEmpty(message = "*email cannot be Empty")
	private String email;
	@NotNull(message = "*mobile cannot be Empty")
	private Long mobile;
	@NotEmpty(message = "*address cannot be Empty")
	private String address;
	private String cardType;
	@NotEmpty(message = "*please select a UID-Type")
	private String uIdType;
	@NotNull(message = "*UID cannot be Empty")
	private Long uId;

	public CreditCardApplicationDto() {
		super();

	}

	public CreditCardApplicationDto(String name, String email, Long mobile, String address, String cardType,
			String uIdType, Long uId) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.cardType = cardType;
		this.uIdType = uIdType;
		this.uId = uId;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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

	@Override
	public String toString() {
		return "CreditCardApplicationDto [name=" + name + ", email=" + email + ", mobile=" + mobile + ", address="
				+ address + ", cardType=" + cardType + ", uIdType=" + uIdType + ", uId=" + uId + "]";
	}

}
