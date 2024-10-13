package com.virtusa.banking.service;

import java.util.List;

import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.AccountType;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.ModeOfTransaction;
import com.virtusa.banking.model.Transaction;
import com.virtusa.banking.model.UserType;

public interface AccountService {

	void depositFunds(Account account, double depositAmount, ModeOfTransaction modeOfTransaction);

	void withdrawFunds(Account account, double withdrawAmount, ModeOfTransaction modeOfTransaction);

	void transferFunds(Account fromAccount, String ifsc, Account toAccount, double amount,
			ModeOfTransaction modeOfTransaction);

	void optedForRecurringTransactions(long accountNumber, boolean optedForRecurringTransactions);
	// ********************************************************************************//
	// getAccountReport (all details of account)

	public boolean addAccount(Account account);

	// Account creation 1, userId is passed if user already exists
	public Account createAccount(String ifsc, AccountType accountType, double balance, int userId);

	// Account creation 2, user info is passed, check if user with same info exists
	// or not
	public Account createAccount(String ifsc, AccountType accountType, double balance, String name, String userAddress,
			long userPhone, String userEmail, UserType userType,String uIdType,Long uId);

	public Account getAccountByNumber(long accountNumber);

	public List<Account> getAccountsByUserId(int userId);

	public List<Long> getAccountNumbersByUserId(int userId);

	public List<Account> getAccountsByIfsc(String ifsc);

	public boolean updateBalance(Account updatedAccount, double finalBalance);

	public Account deleteAccount(long accountNumber);

	public List<Account> getAllAccounts();

	boolean updateAccount(Account updatedAccount);

	boolean updateUserAccess(long accountNumber, boolean access);

	double checkBalanceByAccountNumber(long accountNumber);
	
	List<Transaction> allTranscations(long accountNumber);
	
	public CardDetails applyForCreditCards(int userId,String type);

}
