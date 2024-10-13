package com.virtusa.banking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransferMoneyDto {

	@NotNull(message = "*Please select a account")
	private Long fromAccountNumber;
	@NotEmpty(message = "*IFSC cant be blank")
	private String IFSC;
	@NotNull(message = "*Benificery account number cannot be blank")
	private Long toAccountNumber;
	@Min(value = 100, message = "Transfer amount should be at least 100")
	private Double amount;
	@NotNull(message = "*enter tpin to proceed")
	private Integer tPin;
	private String modeOfTransaction;

	public TransferMoneyDto() {
		super();
	}

	public TransferMoneyDto(Long fromAccountNumber, String iFSC, Long toAccountNumber, Double amount, Integer tPin,
			String modeOfTransaction) {
		super();
		this.fromAccountNumber = fromAccountNumber;
		IFSC = iFSC;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
		this.tPin = tPin;
		this.modeOfTransaction = modeOfTransaction;
	}

	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public Long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer gettPin() {
		return tPin;
	}

	public void settPin(Integer tPin) {
		this.tPin = tPin;
	}

	public String getModeOfTransaction() {
		return modeOfTransaction;
	}

	public void setModeOfTransaction(String modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}

	@Override
	public String toString() {
		return "TransferMoneyDto [fromAccountNumber=" + fromAccountNumber + ", IFSC=" + IFSC + ", toAccountNumber="
				+ toAccountNumber + ", amount=" + amount + ", tPin=" + tPin + ", modeOfTransaction=" + modeOfTransaction
				+ "]";
	}

}
