package com.virtusa.banking.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateBranchDto {
	
	@NotEmpty(message = "Branch Ifsc code cannot be blank.")
	@Size(min = 11, max = 11, message = "Branch Ifsc should be of exactly 11 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Ifsc should contain only alpha numerics")
	private String ifsc;
	
	@NotEmpty(message = "Branch address cannot be blank.")
	@Size(min = 10, max = 50, message = "Branch address should be of atleast 10 characters and should not be more than 50 characters.")
	@Pattern(regexp = "^[.0-9a-zA-Z\\s,-]+$", message = "Address should contain only alphabets, numerics and special characters: , . -")
	private String address;

	public UpdateBranchDto() {
		// TODO Auto-generated constructor stub
	}

	public UpdateBranchDto(String ifsc, String address) {
		super();
		this.ifsc = ifsc;
		this.address = address;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UpdateBranchDto [ifsc=" + ifsc + ", address=" + address + "]";
	}

}
