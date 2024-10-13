package com.virtusa.banking.service;

import java.util.List;

import com.virtusa.banking.model.FinancialProduct;

public interface FinancialProductService {
	boolean applyForFinancialProduct(long accountNumber, FinancialProduct financialProduct);

	boolean addFinancialProduct(FinancialProduct financialProduct);

	boolean createFinancialProduct(String productName, double interestRate, double annualFee, double creditLimit,
			double balanceTransferFee, int accountId, int bankId);

	List<FinancialProduct> getFinancialProductsOfferedByBank(int bankId);

	List<FinancialProduct> getFinancialProductsByUser(int userId);

	List<FinancialProduct> getFinancialProductsByAccount(long AccountNumber);

	FinancialProduct getFinancialProductById(int financialProductId);

	boolean updateFinancialProduct(FinancialProduct financialProduct);

	FinancialProduct deleteFinancialProduct(FinancialProduct financialProduct);

	FinancialProduct deleteFinancialProductById(int financialProductId);

}
