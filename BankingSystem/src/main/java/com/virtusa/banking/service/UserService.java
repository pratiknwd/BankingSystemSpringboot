package com.virtusa.banking.service;

import java.util.List;

import com.virtusa.banking.model.User;
import com.virtusa.banking.model.UserType;

public interface UserService {
	public void createUser(String name, UserType userType, String address, long phoneNo, String email);

	public void addUser(User user);

	public User getUserById(int userId);

	public User getUserByPhone(long phoneNo);

	public User getUserByEmail(String email);

	public List<User> getAllUsers();

	public boolean updateUser(User user);

	public User deleteUser(int userId);

	boolean updateUserName(int userId, String newName);

	boolean updateUserPhone(int userId, Long phonenumber);

	boolean updateUserEmail(int userId, String email);

	boolean updateUserAddressl(int userId, String address);
	
	public List<User> getInactiveUsers();
}
