package com.virtusa.banking.repository;

import java.util.List;

import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.AccountType;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.UserType;

public interface AccountDAO {
	public boolean addAccount(Account account);

	public Account createAccount(long accountNumber, String ifsc, AccountType accountType, double balance, int userId,
			int atmPin, long debitCardNumber,int cvv);

	
	public Account createAccount(long accountNumber, int userId, String ifsc, AccountType accountType, double balance,
			String name, String userAddress, long userPhone, String userEmail, UserType userType, int atmPin,
			long debitCardNumber,int cvv,String uIdType,Long uId);

	public Account getAccountByNumber(long accountNumber);

	public List<Account> getAccountsByUserId(int userId);

	public List<Long> getAccountNumbersByUserId(int userId);

	public List<Account> getAccountsByIfsc(String ifsc);

	public boolean updateBalance(Account updatedAccount, double finalBalance);

	public Account deleteAccount(long accountNumber);

	public List<Account> getAllAccounts();

	boolean updateAccount(Account updatedAccount);

	boolean updateUserAccess(long accountNumber, boolean access);

	boolean updateOptedForRecurringTransactions(long accountNumber, boolean optedForRecurringTransactions);

	double checkBalanceByAccountNumber(long accountNumber);
	
	public List<CardDetails> getCardByAccountNumber(Long account);
	
	public CardDetails applyForCreditCards(CardDetails creditcard);
}
