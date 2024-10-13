package com.virtusa.banking.service;

public interface TransactionService {
	void printAccountStatement(long accountNumber);
	void getAccountTransactionHistory(long accountNumber);
}
