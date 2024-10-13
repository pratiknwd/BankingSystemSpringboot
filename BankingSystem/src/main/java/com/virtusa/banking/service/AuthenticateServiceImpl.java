package com.virtusa.banking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.dto.ResetPassword;
import com.virtusa.banking.exception.ResourceNotFoundException;
import com.virtusa.banking.model.Admin;
import com.virtusa.banking.model.OnlineBankingLoginDetails;
import com.virtusa.banking.model.User;
import com.virtusa.banking.repository.AccountDAO;
import com.virtusa.banking.repository.AdminRespository;
import com.virtusa.banking.repository.BankDAO;
import com.virtusa.banking.repository.OnlineBankingLoginDetailsDAO;
import com.virtusa.banking.repository.UserDAO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	@Autowired
	AccountDAO accountDAOImpl;

	@Autowired
	UserDAO userDAOImpl;

	@Autowired
	OnlineBankingLoginDetailsDAO loginDetailsDAOImpl;

	@Autowired
	BankDAO bankDAOImpl;

	@Autowired
	AdminRespository adminRepository;

	private Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

	// Login and return userId
	@Override
	public int login(int userId, String bankName, String password) {
		OnlineBankingLoginDetails loginDetails = loginDetailsDAOImpl.findByUserId(userId, bankName);
		if (loginDetails != null && loginDetails.getPassword().equals(password)) {
			return userId;
		}

		return 0;
	}

	@Override
	public boolean checkIfRegistered(String bankName, int userId) {
		boolean alreadyRegistered;
		alreadyRegistered = loginDetailsDAOImpl.hasLoginDetails(userId, bankName);

		return alreadyRegistered;

	}

	public boolean isUserPresent(int userId) {

		User user = userDAOImpl.getUserById(userId);
		boolean present = false;
		if (user == null) {
			return present;
		}
		return !present;
	}

	public OnlineBankingLoginDetails getUserDetailsByIdandBankName(int id, String bankName) {

		return loginDetailsDAOImpl.findByUserId(id, bankName);
	}

	public void registerForInternetBanking(Integer userId, String bankName, ResetPassword rp) {

		User user = userDAOImpl.getUserById(userId);
		OnlineBankingLoginDetails newLoginDetails = new OnlineBankingLoginDetails(user, bankName,
				rp.getConfirmPassword());

		loginDetailsDAOImpl.save(newLoginDetails);

		user.setAvailedInternetBanking(true);
		userDAOImpl.updateUser(user);
	}

	@Override
	public int adminLogin(Long adminId, String password) {

		Admin admin = adminRepository.findAdminByAdminId(adminId);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return 1;
			}
		} else {
			throw new ResourceNotFoundException("admin", "adminId", adminId + "");
		}
		logger.info("Inavlid admin credentials");
		return 0;
	}

}
