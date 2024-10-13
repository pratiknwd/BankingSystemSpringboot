package com.virtusa.banking.repository;

import java.util.List;

import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.CardType;

public interface CardDetailsDao {

	// change pin functionality to be added
	List<CardDetails> getCardsByAccountNumberAndType(long accountNumber, CardType cardType);

	CardDetails getCardByCardNumber(long cardNumber);
}
