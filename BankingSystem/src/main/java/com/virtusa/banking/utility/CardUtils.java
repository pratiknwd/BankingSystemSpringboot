package com.virtusa.banking.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardUtils {

	private CardUtils() {
		
	}
	
	public static String formatCardNumber(long cardNumber) {
		String cardNumberStr = String.valueOf(cardNumber);
		return cardNumberStr.replaceAll(".{4}(?=.)", "$0 ");
	}

	public static String formatExpiryDate(LocalDate expiryDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		return expiryDate.format(formatter);
	}
}
