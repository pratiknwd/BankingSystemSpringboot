package com.virtusa.banking.repository;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.exception.ResourceNotFoundException;
import com.virtusa.banking.model.OnlineBankingLoginDetails;

@Repository
public class OnlineBankingLoginDetailsDAOImpl extends BaseRepository implements OnlineBankingLoginDetailsDAO {

	// save login details
	@Override
	@Transactional
	public void save(OnlineBankingLoginDetails details) {

		try {

			super.getHibernateTemplate().save(details);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Retrieve all login details
	@Override
	@Transactional
	public List<OnlineBankingLoginDetails> findAll() {
		try {
			return super.getHibernateTemplate().loadAll(OnlineBankingLoginDetails.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	// update login details
	@Override
	@Transactional
	public void update(OnlineBankingLoginDetails details) {

		try {

			super.getHibernateTemplate().saveOrUpdate(details);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// delete login details
	@Override
	@Transactional
	public void delete(OnlineBankingLoginDetails details) {

		try {

			super.getHibernateTemplate().delete(details);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// find login details of specific user
	@Override
	@Transactional
	public OnlineBankingLoginDetails findByUserId(int userId, String bankName) {
		try {

			String hql = "FROM OnlineBankingLoginDetails WHERE user_id = :userId and bankName = :bankName";
			Query<OnlineBankingLoginDetails> query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hql, OnlineBankingLoginDetails.class);

			query.setParameter("userId", userId);
			query.setParameter("bankName", bankName);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean hasLoginDetails(int userId, String bankName) {
		try {
			// Query to check if a particular user in a bank already has login details
			String hql = "SELECT COUNT(bank) FROM OnlineBankingLoginDetails bank WHERE bank.bankName = :bankName AND user_id = :userId";
			Query<Long> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql,
					Long.class);
			query.setParameter("bankName", bankName);
			query.setParameter("userId", userId);
			// Execute the query and get the count of login details
			Long count = query.uniqueResult();
			return count > 0; // Return true if login details exist, false otherwise
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Return false in case of any exception
		}
	}

	@Override
	public OnlineBankingLoginDetails findByUserId(Integer userId) {
		try {

			String hql = "FROM OnlineBankingLoginDetails WHERE user_id = :userId";
			Query<OnlineBankingLoginDetails> query = super.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hql, OnlineBankingLoginDetails.class);

			query.setParameter("userId", userId);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceNotFoundException("User", "user id", userId+"");
		}
	}

}
