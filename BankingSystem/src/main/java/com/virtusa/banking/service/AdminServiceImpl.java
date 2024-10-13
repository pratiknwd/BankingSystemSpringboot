package com.virtusa.banking.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.model.Admin;
import com.virtusa.banking.repository.AdminRespository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRespository adminRespository;

	@Override
	@Transactional
	public Admin getAdminByAdminId(long id) {
		return adminRespository.findAdminByAdminId(id);
	}

}