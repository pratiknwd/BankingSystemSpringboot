package com.virtusa.banking.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.virtusa.banking.exception.CustomDatabaseException;
import com.virtusa.banking.model.Admin;

@Repository
public class AdminRespository extends BaseRepository {

	@Transactional
	public Admin findAdminByAdminId(long adminId) {
		
		Admin admin = null;
		try {
			admin = super.getHibernateTemplate().get(Admin.class, adminId);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CustomDatabaseException("Database Exception Ocurred ");
		}
		return admin;
		
	}

}
