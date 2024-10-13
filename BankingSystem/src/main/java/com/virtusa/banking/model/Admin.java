package com.virtusa.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	private long adminId;

	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	@Column(name = "user_type", nullable = false, unique = false)
	private String userType;

	public Admin() {
	}

	public Admin(long adminId, String password, String userType) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.userType = userType;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", password=" + password + ", userType=" + userType + "]";
	}

}
