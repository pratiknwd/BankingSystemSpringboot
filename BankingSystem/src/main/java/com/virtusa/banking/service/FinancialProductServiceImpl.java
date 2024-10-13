package com.virtusa.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.model.FinancialProduct;
import com.virtusa.banking.repository.FinancialProductDAO;

@Service
public class FinancialProductServiceImpl implements FinancialProductService {

	@Autowired
	private FinancialProductDAO financialProductDAO;

	// this method needs to be completed
	@Override
	public boolean applyForFinancialProduct(long accountNumber, FinancialProduct financialProduct) {

		return false;
	}

	@Override
	public boolean createFinancialProduct(String productName, double interestRate, double annualFee, double creditLimit,
			double balanceTransferFee, int accountId, int bankId) {
		return financialProductDAO.createFinancialProduct(productName, interestRate, annualFee, creditLimit,
				balanceTransferFee, accountId, bankId);
	}

	@Override
	public boolean addFinancialProduct(FinancialProduct financialProduct) {
		return financialProductDAO.addFinancialProduct(financialProduct);
	}

	@Override
	public List<FinancialProduct> getFinancialProductsOfferedByBank(int bankId) {
		return financialProductDAO.getFinancialProductsByAccount(bankId);
	}

	@Override
	public List<FinancialProduct> getFinancialProductsByUser(int userId) {
		return financialProductDAO.getFinancialProductsByAccount(userId);
	}

	@Override
	public List<FinancialProduct> getFinancialProductsByAccount(long AccountNumber) {
		return financialProductDAO.getFinancialProductsByAccount(AccountNumber);
	}

	@Override
	public FinancialProduct getFinancialProductById(int financialProductId) {
		return financialProductDAO.getFinancialProductById(financialProductId);
	}

	@Override
	public boolean updateFinancialProduct(FinancialProduct financialProduct) {
		return financialProductDAO.updateFinancialProduct(financialProduct);
	}

	@Override
	public FinancialProduct deleteFinancialProduct(FinancialProduct financialProduct) {
		return financialProductDAO.deleteFinancialProduct(financialProduct);
	}

	@Override
	public FinancialProduct deleteFinancialProductById(int financialProductId) {
		return financialProductDAO.deleteFinancialProductById(financialProductId);
	}

}
