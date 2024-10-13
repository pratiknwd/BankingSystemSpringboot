package com.virtusa.banking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.virtusa.banking.model.Bank;

@Repository
public class BankDAOImpl extends BaseRepository implements BankDAO {

	@Override
	@Transactional
	public Bank getBankById(Long id) {

		Bank bank = null;
		try {
			bank = super.getHibernateTemplate().get(Bank.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bank;
	}

	@Override
	@Transactional
	public List<Bank> getAllBanks() {

		List<Bank> banks = null;
		try {
			banks = super.getHibernateTemplate().loadAll(Bank.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return banks;
	}

	@Override
	@Transactional
	public Bank findBankByName(String name) {

		Bank bank = null;
		try {
			bank = (Bank) super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("FROM Bank WHERE bank_name = :name").setParameter("name", name).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bank;
	}

	@Override
	@Transactional
	public long getTotalBanks() {

		long count = 0;
		try {
			count = (long) super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("SELECT COUNT(*) FROM Bank").uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	@Transactional
	public boolean saveBank(Bank bank) {

		try {

			super.getHibernateTemplate().save(bank);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean createBank( String bankName) {

		Bank bank = new Bank();

		try {

			bank.setBankName(bankName);
			super.getHibernateTemplate().save(bank);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateBank(Bank bank) {

		Bank bankExist = null;

		try {

			bankExist = super.getHibernateTemplate().get(Bank.class, bank.getBankId());
			bankExist.setBankId(bank.getBankId());
			bankExist.setBankName(bank.getBankName());
			super.getHibernateTemplate().saveOrUpdate(bankExist);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public Bank deleteBank(int bankId) {
		Bank delBank = null;

		try {

			delBank = super.getHibernateTemplate().get(Bank.class, bankId);
			if (delBank != null)
				super.getHibernateTemplate().delete(delBank);

			return delBank;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return delBank;

	}
}
