package com.virtusa.banking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.model.UniqueIDGenerator;
import com.virtusa.banking.model.User;
import com.virtusa.banking.model.UserType;
import com.virtusa.banking.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserDAO userDAO;
	
	private UniqueIDGenerator uniqueIDGenerator = new UniqueIDGenerator();


	@Override
	public void createUser(String name, UserType userType, String address, long phoneNo, String email) {
		int userId = uniqueIDGenerator.generateUniqueUserId();
		userDAO.createUser(userId, name, userType, address, phoneNo, email);

	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);

	}

	@Override
	@Transactional
	public User getUserById(int userId) {
		return userDAO.getUserById(userId);
	}

	@Override
	public User getUserByPhone(long phoneNo) {
		return userDAO.getUserByPhone(phoneNo);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public User deleteUser(int userId) {
		return userDAO.deleteUser(userId);
	}

	@Override
	public boolean updateUserName(int userId, String newName) {
		return userDAO.updateUserName(userId, newName);
	}

	@Override
	public boolean updateUserPhone(int userId, Long phonenumber) {
		return userDAO.updateUserPhone(userId, phonenumber);
	}

	@Override
	public boolean updateUserEmail(int userId, String email) {
		return userDAO.updateUserEmail(userId, email);
	}

	@Override
	public boolean updateUserAddressl(int userId, String address) {
		return userDAO.updateUserAddressl(userId, address);
	}
	
	public List<User> getInactiveUsers(){
		return userDAO.getInactiveUsers();
	}

}
