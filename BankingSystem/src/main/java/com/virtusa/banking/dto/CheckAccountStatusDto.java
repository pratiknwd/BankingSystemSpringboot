package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CheckAccountStatusDto {

	@NotEmpty(message = "*email can't be empty")
	private String email;

	@NotNull(message = "*applicationNum can't be empty")
	private Long applicationNum;

	public CheckAccountStatusDto() {
		super();
	}

	public CheckAccountStatusDto(String email, Long applicationNum) {
		super();
		this.email = email;
		this.applicationNum = applicationNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(Long applicationNum) {
		this.applicationNum = applicationNum;
	}

	@Override
	public String toString() {
		return "CheckAccountStatusDto [email=" + email + ", applicationNum=" + applicationNum + "]";
	}

}
