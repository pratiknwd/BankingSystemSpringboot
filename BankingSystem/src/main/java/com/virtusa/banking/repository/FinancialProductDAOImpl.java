package com.virtusa.banking.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.Bank;
import com.virtusa.banking.model.FinancialProduct;

@Repository
public class FinancialProductDAOImpl extends BaseRepository implements FinancialProductDAO {

	@Override
	@Transactional
	public boolean createFinancialProduct(String productName, double interestRate, double annualFee, double creditLimit,
			double balanceTransferFee, int accountId, int bankId) {

		try {

			// Create Financial Product
			FinancialProduct financialProduct = new FinancialProduct(productName, interestRate, annualFee, creditLimit,
					balanceTransferFee);

			// Fetch associated entities from the database
			Account account = super.getHibernateTemplate().get(Account.class, accountId);
			Bank bank = super.getHibernateTemplate().get(Bank.class, bankId);

			// Set mappings
			if (account != null) {
				financialProduct.setAccount(account);
				account.getFinancialProducts().add(financialProduct);
				super.getHibernateTemplate().update(account);
			}

			if (bank != null) {
				financialProduct.getBanks().add(bank);
				bank.getFinancialProductsOffered().add(financialProduct);
				super.getHibernateTemplate().update(bank);
			}

			// Save financial product in db
			super.getHibernateTemplate().save(financialProduct);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean addFinancialProduct(FinancialProduct financialProduct) {

		try {

			String hql = "FROM FinancialProduct WHERE productName = :productName";
			Query<FinancialProduct> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery(hql, FinancialProduct.class);
			query.setParameter("productName", financialProduct.getProductName());
			FinancialProduct existingProduct = query.uniqueResult();

			if (existingProduct != null) {
				// Handle the case where the user already exists
				return false;
			}

			super.getHibernateTemplate().save(financialProduct);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	@Transactional
	public List<FinancialProduct> getFinancialProductsOfferedByBank(int bankId) {
		List<FinancialProduct> financialProducts = new ArrayList<>();

		try {
			String hql = "SELECT fp FROM FinancialProduct fp JOIN fp.banks b WHERE b.id = :bankId";
			financialProducts = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery(hql, FinancialProduct.class).setParameter("bankId", bankId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return financialProducts;
	}

	@Override
	@Transactional
	public List<FinancialProduct> getFinancialProductsByUser(int userId) {

		List<FinancialProduct> financialProducts = null;

		try {
			String hql = "SELECT fp FROM FinancialProduct fp " + "JOIN fp.account a " + "JOIN a.user u "
					+ "WHERE u.userId = :userId";
			Query<FinancialProduct> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery(hql, FinancialProduct.class);
			query.setParameter("userId", userId);
			financialProducts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financialProducts;
	}

	@Override
	@Transactional
	public List<FinancialProduct> getFinancialProductsByAccount(long accountNumber) {

		List<FinancialProduct> financialProducts = null;

		try {
			String hql = "FROM FinancialProduct fp WHERE fp.account.accountNumber = :accountNumber";
			Query<FinancialProduct> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery(hql, FinancialProduct.class);
			query.setParameter("accountNumber", accountNumber);
			financialProducts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return financialProducts;

	}

	@Override
	@Transactional
	public FinancialProduct getFinancialProductById(int financialProductId) {

		FinancialProduct financialProduct = null;
		try {
			financialProduct = super.getHibernateTemplate().get(FinancialProduct.class, financialProductId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financialProduct;
	}

	@Override
	@Transactional
	public boolean updateFinancialProduct(FinancialProduct financialProduct) {

		FinancialProduct prodInDB = null;

		try {

			prodInDB = super.getHibernateTemplate().get(FinancialProduct.class, financialProduct.getId());

			prodInDB.setInterestRate(financialProduct.getInterestRate());
			prodInDB.setMaturityPeriodInYears(financialProduct.getMaturityPeriodInYears());
			prodInDB.setMaxInvestment(financialProduct.getMaxInvestment());
			prodInDB.setMinInvestment(financialProduct.getMinInvestment());
			prodInDB.setProductName(financialProduct.getProductName());

			super.getHibernateTemplate().update(prodInDB);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public FinancialProduct deleteFinancialProduct(FinancialProduct financialProduct) {

		try {

			super.getHibernateTemplate().delete(financialProduct);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return financialProduct;
	}

	@Override
	@Transactional
	public FinancialProduct deleteFinancialProductById(int financialProductId) {

		FinancialProduct financialProduct = null;

		try {

			financialProduct = super.getHibernateTemplate().get(FinancialProduct.class, financialProductId);
			if (financialProduct != null) {
				super.getHibernateTemplate().delete(financialProduct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return financialProduct;
	}

}
