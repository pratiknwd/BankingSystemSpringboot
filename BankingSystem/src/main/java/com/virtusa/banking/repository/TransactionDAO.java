package com.virtusa.banking.repository;

import java.util.List;

import com.virtusa.banking.model.Transaction;

public interface TransactionDAO {
	void saveTransaction(Transaction transaction);

	List<com.virtusa.banking.model.Transaction> getAccountStatement(long accountNumber);
}
