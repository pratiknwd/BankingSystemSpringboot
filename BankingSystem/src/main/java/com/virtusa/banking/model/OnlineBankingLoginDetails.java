package com.virtusa.banking.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OnlineBankingLoginDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String bankName;
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	private int tPin;

	public OnlineBankingLoginDetails() {

	}

	public OnlineBankingLoginDetails(User user, String bankName, String password) {
		super();
		this.user = user;
		this.bankName = bankName;
		this.password = password;
		this.tPin = generateSixDigitNumber();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int gettPin() {
		return tPin;
	}

	public void settPin(int tPin) {
		this.tPin = tPin;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public static int generateSixDigitNumber() {
        Random random = new Random();
        int[] digits = new int[10];
        StringBuilder number = new StringBuilder();

        // Generate the first digit (1-9)
        int firstDigit = 1 + random.nextInt(9);
        number.append(firstDigit);
        digits[firstDigit]++;

        // Generate the remaining 5 digits
        while (number.length() < 6) {
            int digit = random.nextInt(10);
            if (digits[digit] < 2) { // Check if the digit can still be used
                digits[digit]++;
                number.append(digit);
            }
        }

        return Integer.parseInt(number.toString());
    }

	@Override
	public String toString() {
		return "OnlineBankingLoginDetails [id=" + id + ", user=" + user + ", bankName=" + bankName + ", password="
				+ password + ", tPin=" + tPin + "]";
	}

}
