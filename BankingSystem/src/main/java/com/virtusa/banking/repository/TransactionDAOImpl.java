package com.virtusa.banking.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.ModeOfTransaction;
import com.virtusa.banking.model.Transaction;
import com.virtusa.banking.model.TransactionType;

@Repository
public class TransactionDAOImpl extends BaseRepository implements TransactionDAO {

	@Override
	@Transactional
	public void saveTransaction(com.virtusa.banking.model.Transaction transaction) {

		try {

			super.getHibernateTemplate().persist(transaction);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public List<Transaction> getAccountStatement(long accountNumber) {

		List<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			Account account2 = super.getHibernateTemplate().get(Account.class, accountNumber);
			if (account2 == null) {
				return transactionList;
			}

			String hql = "FROM Transaction t WHERE t.accountNumber = :accountNumber";
			Query<com.virtusa.banking.model.Transaction> query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hql, Transaction.class);
			query.setParameter("accountNumber", accountNumber);

			transactionList = query.getResultList();
			return transactionList;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

}
