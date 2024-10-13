package com.virtusa.banking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.virtusa.banking.exception.CustomDatabaseException;
import com.virtusa.banking.exception.ResourceNotFoundException;
import com.virtusa.banking.model.Branch;

@Repository
public class BranchDAOImpl extends BaseRepository implements BranchDAO {


	@Override
	@Transactional
	public void addBranch(Branch branch) {
		super.getHibernateTemplate().save(branch);

	}

	@Override
	@Transactional
	public Branch getBranchByIfsc(String ifsc) {

		Branch branch = null;

		try {
			branch = (Branch) super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("FROM Branch WHERE ifsc = :ifsc").setParameter("ifsc", ifsc).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return branch;
	}

	@Override
	@Transactional
	public List<Branch> getBranchesByBankId(Long bankId) {

		List<Branch> branches = null;

		try {
			branches = super.getHibernateTemplate().getSessionFactory().getCurrentSession()
					.createQuery("FROM Branch WHERE bank.id = :bankId", Branch.class).setParameter("bankId", bankId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return branches;
	}

	@Override
	@Transactional
	public boolean updateBranch(Branch branch) {

		try {

			super.getHibernateTemplate().update(branch);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public Branch deleteBranch(String ifsc) {

		Branch branch = super.getHibernateTemplate().get(Branch.class, ifsc);
		if(branch==null) {
			throw new ResourceNotFoundException("Branch", "Ifsc", ifsc);
		}
		try {

			super.getHibernateTemplate().delete(branch);
		} catch (Exception e) {

			e.printStackTrace();
			throw new CustomDatabaseException("Database Exception Occured, Branch Deletion Failed!!");
		}
		return branch;
	}
	
	@Override
	@Transactional
	public Branch deleteBranch(Branch branch) {

		try {

			super.getHibernateTemplate().delete(branch);
		} catch (Exception e) {

			e.printStackTrace();
			throw new CustomDatabaseException("Database Exception Occured, Branch Deletion Failed!!");
		}
		return branch;
	}

	@Override
	@Transactional
	public List<Branch> getAllBranches() {

		List<Branch> branches = null;

		try {
			branches = super.getHibernateTemplate().loadAll(Branch.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return branches;
	}
}
