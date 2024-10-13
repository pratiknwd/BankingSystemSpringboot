package com.virtusa.banking.service;

import java.util.List;

import com.virtusa.banking.model.Bank;

public interface BankService {
	Bank getBankById(Long id);

	List<Bank> getAllBanks();

	Bank findBankByName(String name);

	long getTotalBanks();

	boolean saveBank(Bank bank);

	boolean createBank(String bankName);

	boolean updateBank(Bank bank);

	Bank deleteBank(int bankId);
}
