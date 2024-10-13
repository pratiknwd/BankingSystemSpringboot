package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;

public class ResetPassword {

	@NotEmpty(message = "*field cannot be empty")
	private String oldPassword;
	@NotEmpty(message = "*field cannot be empty")
	private String newPassword;
	@NotEmpty(message = "*field cannot be empty")
	private String confirmPassword;

	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResetPassword(String oldPassword, String newPassword, String confirmPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ChangePassword [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + "]";
	}

}
