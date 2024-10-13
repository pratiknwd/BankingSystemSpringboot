package com.virtusa.banking.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class UniqueIDGenerator {
	private Set<Integer> generatedBankIDs; // Track all generated bank IDs
	private Set<Long> generatedAccountNumbers; // Track all generated account numbers
	private Set<Integer> generatedUserIDs; // Track all generated userIDs
	private Set<Long> generatedCardNumbers; // Track all generated card numbers
	private Set<Integer> generatedAtmPins; // Track all generated ATM PINs

	public UniqueIDGenerator() {
		this.generatedBankIDs = new HashSet<>();
		this.generatedAccountNumbers = new HashSet<>();
		this.generatedUserIDs = new HashSet<>();
		this.generatedCardNumbers = new HashSet<>();
		this.generatedAtmPins = new HashSet<>();
	}

	public int generateUnique6DigitBankID() {
		int newCode;

		do {
			UUID uniqueId = UUID.randomUUID(); // Generate UUID (36-character string with a specific format: hexadecimal
												// digits separated by hyphens)
			int hashCode = Math.abs(uniqueId.hashCode()); // Get absolute hash code
			newCode = hashCode % 1000000; // Create 6-digit code
		} while (generatedBankIDs.contains(newCode)); // Ensure it's unique

		generatedBankIDs.add(newCode); // Add to the set of generated codes
		return newCode;
	}

	public long generateUnique14DigitAccountNumber() {

		UUID uniqueId;
		long accountNumber;
		String uuidString;
		String accountNumberStr;

		do {
			uniqueId = UUID.randomUUID(); // Generate UUID
			uuidString = uniqueId.toString().replace("-", ""); // Remove hyphens
			accountNumberStr = uuidString.substring(0, 14); // // Take the first 14 characters and convert them to a
															// number
			accountNumber = new BigInteger(accountNumberStr, 16).longValue(); // Convert from hexadecimal to long
		} while (generatedAccountNumbers.contains(accountNumber)); // Ensure it's unique

		generatedAccountNumbers.add(accountNumber); // Add to the set of generated codes
		return accountNumber;
	}

	public long generateUnique16DigitCardNumber() {
		long cardNumber;
		String cardNumberStr;

		do {
			UUID uniqueId = UUID.randomUUID(); // Generate UUID
			String uuidString = uniqueId.toString().replace("-", ""); // Remove hyphens

			// Take the first 16 characters and convert them to a number
			cardNumberStr = uuidString.substring(0, 16);

			// Convert from hexadecimal to long and ensure it's non-negative
			cardNumber = new BigInteger(cardNumberStr, 16).abs().longValue();

			// Ensure it is 16 digits by adding padding if necessary
			cardNumberStr = String.format("%016d", cardNumber);
			cardNumber = Long.parseLong(cardNumberStr);
		} while (generatedCardNumbers.contains(cardNumber) || cardNumberStr.length() != 16
				|| cardNumberStr.charAt(0) == '0' || cardNumberStr.charAt(0) == '-');

		generatedCardNumbers.add(cardNumber); // Add to the set of generated codes
		return cardNumber;
	}

	// Method to generate a unique user ID
	public int generateUniqueUserId() {
		Random random = new Random();
		int userId;

		do {
			userId = 1000 + random.nextInt(9000); // Generate a 4-digit user ID
		} while (generatedUserIDs.contains(userId)); // Ensure uniqueness

		return userId;
	}

	// Method to generate a unique ATM PIN
	public int generateUniqueATMPIN() {
		Random random = new Random();
		int atmPin;

		do {
			atmPin = 1000 + random.nextInt(9000); // Generate a 4-digit ATM PIN
		} while (generatedAtmPins.contains(atmPin)); // Ensure uniqueness

		return atmPin;
	}

	public int generateCvv() {
		Random random = new Random();
		return 100 + random.nextInt(900);
	}

}
