package com.virtusa.banking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id", nullable = false, unique = true)
	private int userId;

	@Column(name = "name", nullable = false, unique = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false, unique = false)
	private UserType userType;

	@Column(name = "address", nullable = false, unique = false)
	private String address;

	@Column(name = "phone_no", nullable = false, unique = false)
	private long phoneNo;

	@Column(name = "alt_phone_no", nullable = false, unique = false)
	private long altPhoneNo;

	@Column(name = "email", nullable = false, unique = false) // changed unique from true to false, since there can be
																// more than one account associated with the same email.
	private String email;

	private boolean availedInternetBanking = false;
	@Column(name = "overdraft_limit", nullable = true)

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<OnlineBankingLoginDetails> loginDetails = new ArrayList<>();

	private String status;

	private String uIdType;

	private Long uId;

	public User() {
	}

	public User(int userId, String name, UserType userType, String address, long phoneNo, long altPhoneNo, String email,
			boolean availedInternetBanking, List<Account> accounts, List<OnlineBankingLoginDetails> loginDetails,
			String uIdType, Long uId) {
		super();
		this.userId = userId;
		this.name = name;
		this.userType = userType;
		this.address = address;
		this.phoneNo = phoneNo;
		this.altPhoneNo = altPhoneNo;
		this.email = email;
		this.availedInternetBanking = availedInternetBanking;
		this.accounts = accounts;
		this.loginDetails = loginDetails;
		this.status = "Inactive";
		this.uIdType = uIdType;
		this.uId = uId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getAltPhoneNo() {
		return altPhoneNo;
	}

	public void setAltPhoneNo(long altPhoneNo) {
		this.altPhoneNo = altPhoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<OnlineBankingLoginDetails> getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(List<OnlineBankingLoginDetails> loginDetails) {
		this.loginDetails = loginDetails;
	}

	public boolean isAvailedInternetBanking() {
		return availedInternetBanking;
	}

	public void setAvailedInternetBanking(boolean availedInternetBanking) {
		this.availedInternetBanking = availedInternetBanking;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "User [userId=" + userId + ", name=" + name + ", userType=" + userType + ", address=" + address
				+ ", phoneNo=" + phoneNo + ", altPhoneNo=" + altPhoneNo + ", email=" + email
				+ ", availedInternetBanking=" + availedInternetBanking + ", status=" + status + ", uIdType=" + uIdType
				+ ", uId=" + uId + "]";
	}

}