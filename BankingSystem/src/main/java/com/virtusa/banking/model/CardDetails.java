package com.virtusa.banking.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CardDetails {
	@Id
	@Column(name = "card_number", nullable = false, unique = true)
	private long cardNumber;
	@Column(name = "card_pin", nullable = false, unique = false)
	private int cardPin;
	private CardType cardType;
	private int cvv;
	private LocalDate expiry;
	@ManyToOne
	@JoinColumn(name = "account_number", nullable = false)
	private Account account;

	private String type;

	public CardDetails() {
	}

	public CardDetails(long cardNumber, int cardPin, CardType cardType, int cvv, LocalDate expiry, Account account,
			String type) {
		super();
		this.cardNumber = cardNumber;
		this.cardPin = cardPin;
		this.cardType = cardType;
		this.cvv = cvv;
		this.expiry = expiry;
		this.account = account;
		this.type = type;
	}

	public int getCardPin() {
		return cardPin;
	}

	public void setCardPin(int cardPin) {
		this.cardPin = cardPin;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public LocalDate getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDate expiry) {
		this.expiry = expiry;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CardDetails [cardNumber=" + cardNumber + ", cardPin=" + cardPin + ", cardType=" + cardType + ", cvv="
				+ cvv + ", expiry=" + expiry + ", account=" + account + ", type=" + type + "]";
	}

}
