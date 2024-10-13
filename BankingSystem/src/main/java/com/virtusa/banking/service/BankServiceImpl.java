package com.virtusa.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.model.Bank;
import com.virtusa.banking.repository.BankDAO;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAO bankDAO;

	@Override
	public Bank getBankById(Long id) {
		return bankDAO.getBankById(id);
	}

	@Override
	public List<Bank> getAllBanks() {
		return bankDAO.getAllBanks();
	}

	@Override
	public Bank findBankByName(String name) {
		return bankDAO.findBankByName(name);
	}

	@Override
	public long getTotalBanks() {
		return bankDAO.getTotalBanks();
	}

	@Override
	public boolean saveBank(Bank bank) {
		return bankDAO.saveBank(bank);
	}

	@Override
	public boolean createBank(String bankName) {
		return bankDAO.createBank(bankName);

	}

	@Override
	public boolean updateBank(Bank bank) {
		return bankDAO.updateBank(bank);

	}

	@Override
	public Bank deleteBank(int bankId) {
		return bankDAO.deleteBank(bankId);
	}

}
