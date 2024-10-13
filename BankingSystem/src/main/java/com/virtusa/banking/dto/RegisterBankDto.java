package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterBankDto {
	
	@NotEmpty(message = "Bank name cannot be blank.")
	@Size(min = 2,max = 5, message = "Bank name should be of atleast 2 characters and should not be more than 5 characters.")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
	private String bankName;

	public RegisterBankDto() {
		// TODO Auto-generated constructor stub
	}

	public RegisterBankDto(String bankName) {
		super();
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "RegisterBankDto [bankName=" + bankName + "]";
	}

	
}
