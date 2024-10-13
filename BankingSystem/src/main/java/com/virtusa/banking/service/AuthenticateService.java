package com.virtusa.banking.service;

public interface AuthenticateService {
	
	// Check if user has already registered for Internet Banking
	boolean checkIfRegistered(String bankName, int userId);
	
	// Login and return account object
	int login(int userId, String bankName, String password);

	int adminLogin(Long userId, String password);

}
