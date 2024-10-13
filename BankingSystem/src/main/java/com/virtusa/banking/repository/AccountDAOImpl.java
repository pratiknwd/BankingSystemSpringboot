package com.virtusa.banking.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.exception.ResourceNotFoundException;
import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.AccountType;
import com.virtusa.banking.model.Branch;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.CardType;
import com.virtusa.banking.model.User;
import com.virtusa.banking.model.UserType;

@Repository
public class AccountDAOImpl extends BaseRepository implements AccountDAO {

	private Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

	private String sessionFactoryNullmessage = "Session Factory is Null";

	@Override
	@Transactional
	public Account getAccountByNumber(long accountNumber) {

		Account account = null;

		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			account = (Account) sessionFactory.getCurrentSession()
					.createQuery("FROM Account WHERE account_number = :accountNumber")
					.setParameter("accountNumber", accountNumber).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	@Transactional
	public List<Account> getAccountsByUserId(int userId) {

		List<Account> accounts = null;
		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			accounts = sessionFactory.getCurrentSession()
					.createQuery("FROM Account WHERE user_Id = :userId", Account.class).setParameter("userId", userId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	@Transactional
	public List<Long> getAccountNumbersByUserId(int userId) {

		List<Account> accounts = null;
		List<Long> accountNumbersList = new ArrayList<>();

		try {
			accounts = getAccountsByUserId(userId);
			if (accounts == null) {
				logger.info("You dont have any accounts yet. Please create an account.");
				return null;
			}
			for (Account a : accounts) {
				accountNumbersList.add(a.getAccountNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountNumbersList;
	}

	@Override
	@Transactional
	public List<Account> getAccountsByIfsc(String ifsc) {

		List<Account> accounts = null;

		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			accounts = sessionFactory.getCurrentSession().createQuery("FROM Account WHERE ifsc = :ifsc", Account.class)
					.setParameter("ifsc", ifsc).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	@Transactional
	public boolean addAccount(Account account) {

		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			Account accountExists = (Account) sessionFactory.getCurrentSession()
					.createQuery("FROM Account WHERE accountNumber = :account_number")
					.setParameter("account_number", account.getAccountNumber()).uniqueResult();

			if (accountExists == null) {

				super.getHibernateTemplate().save(account);

				return true;
			} else {
				logger.info("Account with account number - {} already exists!", account.getAccountNumber());
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public Account createAccount(long accountNumber, String ifsc, AccountType accountType, double balance, int userId,
			int atmPin, long debitCardNumber, int cvv) {

		try {

			User user = super.getHibernateTemplate().get(User.class, userId);
			if (user == null) {
				throw new ResourceNotFoundException("User", "userId", userId + "");
			}

			CardDetails cardDetails = new CardDetails();
			cardDetails.setCardNumber(debitCardNumber);
			cardDetails.setCardType(CardType.DebitCard);
			cardDetails.setCardPin(atmPin);
			cardDetails.setCvv(cvv);
			cardDetails.setType("Default");
			cardDetails.setExpiry(LocalDate.now().plusYears(5));

			Branch branch = super.getHibernateTemplate().get(Branch.class, ifsc);
			if (branch == null) {
				throw new ResourceNotFoundException("Branch", "IFSC ", ifsc);
			}

			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setIfsc(ifsc);
			account.setBranch(branch);
			account.setAccountType(accountType);
			account.setBalance(balance);
			account.setUser(user);
			account.getCardDetails().add(cardDetails);

			cardDetails.setAccount(account);

			super.getHibernateTemplate().save(account);

			super.getHibernateTemplate().save(cardDetails);

			user.getAccounts().add(account);
			super.getHibernateTemplate().update(user);

			return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * if user doesn't exists,user details are passed in this method which will
	 * create both account and user and save them both in database
	 **/
	@Override
	@Transactional
	public Account createAccount(long accountNumber, int userId, String ifsc, AccountType accountType, double balance,
			String name, String userAddress, long userPhone, String userEmail, UserType userType, int atmPin,
			long debitCardNumber, int cvv, String uIdType, Long uId) {

		try {

			Branch branch = super.getHibernateTemplate().get(Branch.class, ifsc);
			if (branch == null) {
				throw new ResourceNotFoundException("Branch", "IFSC ", ifsc);
			}

			User user = new User();
			user.setName(name);
			user.setUserType(userType);
			user.setAddress(userAddress);
			user.setPhoneNo(userPhone);
			user.setEmail(userEmail);
			user.setUserId(userId);
			user.setStatus("Inactive");
			user.setuIdType(uIdType);
			user.setuId(uId);

			CardDetails cardDetails = new CardDetails();
			cardDetails.setCardNumber(debitCardNumber);
			cardDetails.setCardType(CardType.DebitCard);
			cardDetails.setCardPin(atmPin);
			cardDetails.setCvv(cvv);
			cardDetails.setType("Default");
			cardDetails.setExpiry(LocalDate.now().plusYears(5));

			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setIfsc(ifsc);
			account.setBranch(branch);
			account.setAccountType(accountType);
			account.setBalance(balance);
			account.setUser(user);
			account.getCardDetails().add(cardDetails);
			cardDetails.setAccount(account);
			user.getAccounts().add(account);
			super.getHibernateTemplate().save(user);
			super.getHibernateTemplate().save(account);
			super.getHibernateTemplate().save(cardDetails);
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean updateAccount(Account updatedAccount) {

		Account account = null;

		try {
			account = super.getHibernateTemplate().get(Account.class, updatedAccount.getAccountNumber());
			if (account == null) {
				throw new NullPointerException("account is null");
			}
			account.setBalance(updatedAccount.getBalance());
			super.getHibernateTemplate().update(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	@Transactional
	public boolean updateBalance(Account updatedAccount, double finalBalance) {
		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			@SuppressWarnings("unchecked")
			Query<Account> query = sessionFactory.getCurrentSession()
					.createQuery("UPDATE Account SET balance = :balance WHERE accountNumber = :accountNumber");
			query.setParameter("balance", finalBalance);
			query.setParameter("accountNumber", updatedAccount.getAccountNumber());

			int result = query.executeUpdate();

			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateUserAccess(long accountNumber, boolean access) {

		Account accountInDB = null;

		try {
			accountInDB = super.getHibernateTemplate().get(Account.class, accountNumber);
			if (accountInDB == null) {
				throw new NullPointerException("AccountInDB is null");
			}
			accountInDB.setAccess(access);
			super.getHibernateTemplate().saveOrUpdate(accountInDB);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateOptedForRecurringTransactions(long accountNumber, boolean optedForRecurringTransactions) {

		Account accountInDB = null;

		try {
			accountInDB = super.getHibernateTemplate().get(Account.class, accountNumber);
			if (accountInDB == null) {
				throw new NullPointerException("AccountInDB is null");
			}
			accountInDB.setOptedForRecurringTransaction(optedForRecurringTransactions);

			super.getHibernateTemplate().saveOrUpdate(accountInDB);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public Account deleteAccount(long accountNumber) {

		try {

			Account account = super.getHibernateTemplate().get(Account.class, accountNumber);

			if (account != null) {

				super.getHibernateTemplate().delete(account);
				return account;
			} else {
				logger.info("Account with account number {} not found.", accountNumber);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<Account> getAllAccounts() {
		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			return sessionFactory.getCurrentSession().createQuery("FROM Account", Account.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public double checkBalanceByAccountNumber(long accountNumber) {
		double balance = 0;
		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			balance = (double) sessionFactory.getCurrentSession()
					.createQuery("SELECT a.balance FROM Account a WHERE a.accountNumber = :account_number")
					.setParameter("account_number", accountNumber).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	@Transactional
	public List<CardDetails> getCardByAccountNumber(Long accNum) {

		List<CardDetails> cards = null;
		SessionFactory sessionFactory = super.getHibernateTemplate().getSessionFactory();
		try {
			if (sessionFactory == null) {
				throw new NullPointerException(sessionFactoryNullmessage);
			}
			cards = sessionFactory.getCurrentSession()
					.createQuery("FROM CardDetails WHERE account_number = :accNum", CardDetails.class)
					.setParameter("accNum", accNum).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cards;
	}

	@Override
	@Transactional
	public CardDetails applyForCreditCards(CardDetails creditcard) {
		try {
			super.getHibernateTemplate().save(creditcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creditcard;
	}

}
