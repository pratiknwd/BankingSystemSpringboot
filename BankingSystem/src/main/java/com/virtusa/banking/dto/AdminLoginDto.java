package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminLoginDto {

	@NotNull(message = "*userId cannot be blank")
	private Long adminId;
	@NotEmpty(message = "*password cannot be blank")
	private String password;

	public AdminLoginDto() {

	}

	public AdminLoginDto(Long adminId, String password) {
		super();
		this.adminId = adminId;
		this.password = password;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminLoginDto [adminId=" + adminId + ", password=" + password + "]";
	}

}
