package com.virtusa.banking.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.AccountType;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.CardType;
import com.virtusa.banking.model.ModeOfTransaction;
import com.virtusa.banking.model.Transaction;
import com.virtusa.banking.model.TransactionType;
import com.virtusa.banking.model.UniqueIDGenerator;
import com.virtusa.banking.model.UserType;
import com.virtusa.banking.repository.AccountDAO;
import com.virtusa.banking.repository.TransactionDAO;

@Service
public class AccountServiceImpl implements AccountService {

	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private AccountDAO accountDAO;

	private TransactionDAO transactionDAO;

	@Autowired
	public AccountServiceImpl(AccountDAO accountDAO, TransactionDAO transactionDAO) {
		super();
		this.accountDAO = accountDAO;
		this.transactionDAO = transactionDAO;
	}

	private final UniqueIDGenerator uniqueIDGenerator = new UniqueIDGenerator();

	public AccountServiceImpl() {

	}

	@Override
	public void depositFunds(Account account, double depositAmount, ModeOfTransaction modeOfTransaction) {
		int warningMaxLimit = 100000;

		if (account == null) {
			logger.info("Account doesn't exists");
			return;
		}

		if (!account.isAccess()) {
			logger.info("Your account is blocked. Please contact your bank.");
			return;
		}
		if (depositAmount > warningMaxLimit) {
			logger.info("Amount > {}. This transaction will be tracked !", warningMaxLimit);
		} else if (depositAmount < 0) {
			logger.info("Amount can't be negative. Transaction failed !");
			return;
		}

		double initialBalance = account.getBalance();
		account.deposit(depositAmount); // this is only done for showing output in the console.
		double finalBalance = initialBalance + depositAmount;
		accountDAO.updateBalance(account, finalBalance);

		// Create and save the transaction
		com.virtusa.banking.model.Transaction transaction = new com.virtusa.banking.model.Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setAccount(account);
		transaction.setModeOfTransaction(modeOfTransaction);
		transaction.setAmount(depositAmount);
		transaction.setDateTime(new Date());
		transaction.setTransactionType(TransactionType.CR);
		transaction.setBalanceRemaining(account.getBalance());

		transactionDAO.saveTransaction(transaction);

		logger.info("Amount : {} deposited to your account number - {} successfully.", depositAmount,
				account.getAccountNumber());
		logger.info("New Balance : {}", account.getBalance());

	}

	@Override
	public void withdrawFunds(Account account, double withdrawAmount, ModeOfTransaction modeOfTransaction) {
		if (account == null) {
			logger.info("Account doesn't exists");
			return;
		}
		// Implementation of deposit funds
		if (!account.isAccess()) {
			logger.info("Your account is blocked. Please contact your bank.");
			return;
		}
		if (withdrawAmount > 100000) {
			logger.info("Amount > 100000. This transaction will be tracked !");
		} else if (withdrawAmount < 0) {
			logger.info("Amount can't be negative. Transaction failed !");
			return;
		}

		double initialBalance = account.getBalance();
		double finalBalance = initialBalance;
		account.withdraw(withdrawAmount); // this is only done for showing output in the console.
		if (withdrawAmount <= initialBalance) {
			finalBalance = initialBalance - withdrawAmount;
		} else {
			logger.info("Withdraw amount cannot be more than balance");
			return;
		}
		accountDAO.updateBalance(account, finalBalance);

		// Create and save the transaction
		com.virtusa.banking.model.Transaction transaction = new com.virtusa.banking.model.Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setAccount(account);
		transaction.setModeOfTransaction(modeOfTransaction);
		transaction.setAmount(withdrawAmount);
		transaction.setDateTime(new Date());
		transaction.setTransactionType(TransactionType.DB);
		transaction.setBalanceRemaining(account.getBalance());

		transactionDAO.saveTransaction(transaction);
		String withdrawnAmount = BigDecimal.valueOf(withdrawAmount).toPlainString();
		logger.info("Amount : {} withdrawn from your account number - {}  successfully.", withdrawnAmount,
				account.getAccountNumber());
		String accountBalance = BigDecimal.valueOf(account.getBalance()).toPlainString();
		logger.info("New Balance : {} ", accountBalance);

	}

	@Override
	public void transferFunds(Account fromAccount, String ifsc, Account toAccount, double amount,
			ModeOfTransaction modeOfTransaction) {
		// Implementation of transfer funds

		// if fromAccount and toAccount Exist

		if (fromAccount == null || toAccount == null) {
			logger.info("Either of the accounts doesn't exist!! Transaction failed! ");
			return;
		}

		if (!fromAccount.isAccess() || !toAccount.isAccess()) {
			logger.info("Either of the accounts is blocked. Please contact your bank.");
			return;
		}

		// if amount is quite large,track the transaction.
		if (amount > 100000) {
			logger.info("Amount > 100000. This transaction will be tracked !");
		}
		// if amount is negative
		else if (amount <= 0) {
			logger.info("Amount can't be negative or 0. Transaction failed !");
			return;

		}
		// if balance in fromAccount is less than transferring amount
		else if (fromAccount.getBalance() < amount) {
			logger.info("Insufficient Balance. Transaction failed !");
			return;
		}

		fromAccount.withdraw(amount);
		toAccount.deposit(amount);

		accountDAO.updateBalance(fromAccount, fromAccount.getBalance());
		accountDAO.updateBalance(toAccount, toAccount.getBalance());

		// Create and save the transaction for fromAccount
		com.virtusa.banking.model.Transaction transactionFrom = new com.virtusa.banking.model.Transaction();
		transactionFrom.setAccountNumber(fromAccount.getAccountNumber());
		transactionFrom.setRefAccountNumber(toAccount.getAccountNumber());
		transactionFrom.setAccount(fromAccount);
		transactionFrom.setModeOfTransaction(modeOfTransaction);
		transactionFrom.setAmount(amount);
		transactionFrom.setDateTime(new Date());
		transactionFrom.setTransactionType(TransactionType.DB);
		transactionFrom.setBalanceRemaining(fromAccount.getBalance());

		transactionDAO.saveTransaction(transactionFrom);

		// Create and save the transaction for toAccount
		com.virtusa.banking.model.Transaction transactionTo = new com.virtusa.banking.model.Transaction();
		transactionTo.setAccountNumber(toAccount.getAccountNumber());
		transactionTo.setRefAccountNumber(fromAccount.getAccountNumber());
		transactionTo.setAccount(toAccount);
		transactionTo.setModeOfTransaction(modeOfTransaction);
		transactionTo.setAmount(amount);
		transactionTo.setDateTime(new Date());
		transactionTo.setTransactionType(TransactionType.CR);
		transactionTo.setBalanceRemaining(toAccount.getBalance());

		transactionDAO.saveTransaction(transactionTo);
		String balance = BigDecimal.valueOf(amount).toPlainString();
		logger.info(
				"Amount : {} transferred from your account number - {}\n to account with account number - {} successfully.",
				balance, fromAccount.getAccountNumber(), toAccount.getAccountNumber());

	}

	public void optedForRecurringTransactions(long accountNumber, boolean optedForRecurringTransactions) {
		Account accountExists = accountDAO.getAccountByNumber(accountNumber);

		if (accountExists != null) {
			accountDAO.updateOptedForRecurringTransactions(accountNumber, optedForRecurringTransactions);
		} else {
			logger.info("Account with provided account number does not exist!");
		}
	}

	// with user id (user exists)
	@Override
	public Account createAccount(String ifsc, AccountType accountType, double balance, int userId) {
		long accountNumber = uniqueIDGenerator.generateUnique14DigitAccountNumber();
		long debitCardNumber = uniqueIDGenerator.generateUnique16DigitCardNumber();
		int atmPin = uniqueIDGenerator.generateUniqueATMPIN();
		int cvv = uniqueIDGenerator.generateCvv();
		return accountDAO.createAccount(accountNumber, ifsc, accountType, balance, userId, atmPin, debitCardNumber,
				cvv);
	}

	// without user id (will also create a user in table)
	@Override
	public Account createAccount(String ifsc, AccountType accountType, double balance, String name, String userAddress,
			long userPhone, String userEmail, UserType userType, String uIdType, Long uId) {
		long debitCardNumber = uniqueIDGenerator.generateUnique16DigitCardNumber();
		int atmPin = uniqueIDGenerator.generateUniqueATMPIN();
		long accountNumber = uniqueIDGenerator.generateUnique14DigitAccountNumber();
		int userId = uniqueIDGenerator.generateUniqueUserId();
		int cvv = uniqueIDGenerator.generateCvv();
		return accountDAO.createAccount(accountNumber, userId, ifsc, accountType, balance, name, userAddress, userPhone,
				userEmail, userType, atmPin, debitCardNumber, cvv, uIdType, uId);
	}

	// Delegating methods to DAO
	@Override
	public Account getAccountByNumber(long accountNumber) {
		return accountDAO.getAccountByNumber(accountNumber);
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		return accountDAO.getAccountsByUserId(userId);
	}

	public List<List<CardDetails>> getCardByUserId(int userId) {

		List<Long> account = getAccountNumbersByUserId(userId);
		List<List<CardDetails>> cards = new ArrayList<>();

		for (Long acc : account) {
			cards.add(accountDAO.getCardByAccountNumber(acc));
		}

		return cards;

	}

	@Override
	public List<Long> getAccountNumbersByUserId(int userId) {
		return accountDAO.getAccountNumbersByUserId(userId);
	}

	@Override
	public List<Account> getAccountsByIfsc(String ifsc) {
		return accountDAO.getAccountsByIfsc(ifsc);
	}

	@Override
	public boolean updateBalance(Account updatedAccount, double finalBalance) {
		return accountDAO.updateBalance(updatedAccount, finalBalance);
	}

	@Override
	public Account deleteAccount(long accountNumber) {
		return accountDAO.deleteAccount(accountNumber);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}

	@Override
	public boolean updateAccount(Account updatedAccount) {
		return accountDAO.updateAccount(updatedAccount);
	}

	@Override
	public boolean updateUserAccess(long accountNumber, boolean access) {
		return accountDAO.updateUserAccess(accountNumber, access);
	}

	@Override
	public boolean addAccount(Account account) {
		return accountDAO.addAccount(account);
	}

	@Override
	public double checkBalanceByAccountNumber(long accountNumber) {
		return accountDAO.checkBalanceByAccountNumber(accountNumber);
	}

	@Override
	public List<Transaction> allTranscations(long accountNumber) {
		List<Transaction> accountStatement = null;
		accountStatement = transactionDAO.getAccountStatement(accountNumber);
		return accountStatement;
	}

	@Override
	public CardDetails applyForCreditCards(int userId, String type) {

		List<Account> accountsByUserId = getAccountsByUserId(userId);

		CardDetails credit = new CardDetails();
		credit.setAccount(accountsByUserId.get(0));
		credit.setCardNumber(uniqueIDGenerator.generateUnique16DigitCardNumber());
		credit.setCardPin(uniqueIDGenerator.generateUniqueATMPIN());
		credit.setCardType(CardType.CreditCard);
		credit.setCvv(uniqueIDGenerator.generateCvv());
		credit.setExpiry(LocalDate.now().plusYears(5));
		credit.setType(type);

		return accountDAO.applyForCreditCards(credit);
	}
}
