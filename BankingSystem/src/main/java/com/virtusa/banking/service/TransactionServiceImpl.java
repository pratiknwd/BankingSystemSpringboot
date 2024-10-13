package com.virtusa.banking.service;

import org.springframework.stereotype.Service;

import com.virtusa.banking.repository.TransactionDAOImpl;

@Service
public class TransactionServiceImpl {
	TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
	
	public void printAccountStatement(long accountNumber) {
		transactionDAOImpl.getAccountStatement(accountNumber);
	}
	
}
