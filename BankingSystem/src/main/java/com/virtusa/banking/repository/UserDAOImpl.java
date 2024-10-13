package com.virtusa.banking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.banking.model.User;
import com.virtusa.banking.model.UserType;

@Repository
public class UserDAOImpl extends BaseRepository implements UserDAO {


	public void createUser(int userId, String name, UserType userType, String address, long phoneNo, String email) {

		// logic
		try {
			User user = new User();
			user.setUserId(userId);
			user.setName(name);
			user.setUserType(userType);
			user.setAddress(address);
			user.setPhoneNo(phoneNo);
			user.setEmail(email);

			super.getHibernateTemplate().save(user);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Transactional
	public void addUser(User user) {

		try {
			// Check if a user with the same email already exists
			String hql = "FROM User WHERE email = :email";
			Query<User> query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql,
					User.class);
			query.setParameter("email", user.getEmail());
			User existingUser = query.uniqueResult();

			if (existingUser != null) {
				// Handle the case where the user already exists
				return;
			}

			super.getHibernateTemplate().save(user);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public User getUserById(int userId) {

		User user = null;
		try {
			user = super.getHibernateTemplate().get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {

		try {

			super.getHibernateTemplate().update(user);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	@Transactional
	public boolean updateUserName(int userId, String newName) {

		User userInDB = null;

		try {

			userInDB = super.getHibernateTemplate().get(User.class, userId);
			userInDB.setName(newName);
			super.getHibernateTemplate().update(userInDB);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateUserPhone(int userId, Long phonenumber) {

		User userInDB = null;

		try {
			userInDB = super.getHibernateTemplate().get(User.class, userId);
			userInDB.setPhoneNo(phonenumber);
			super.getHibernateTemplate().update(userInDB);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateUserEmail(int userId, String email) {

		User userInDB = null;

		try {
			userInDB = super.getHibernateTemplate().get(User.class, userId);
			userInDB.setEmail(email);
			super.getHibernateTemplate().update(userInDB);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateUserAddressl(int userId, String address) {

		User userInDB = null;

		try {
			userInDB = super.getHibernateTemplate().get(User.class, userId);
			userInDB.setAddress(address);
			super.getHibernateTemplate().update(userInDB);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	@Transactional
	public User deleteUser(int userId) {

		User delUser = null;
		try {

			delUser = super.getHibernateTemplate().get(User.class, userId);
			if(delUser == null) {
				throw new NullPointerException("DelUser is null");
			}
			super.getHibernateTemplate().delete(delUser);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return delUser;
	}

	@Override
	@Transactional
	public User getUserByPhone(long phoneNo) {

		User user = null;
		try {
			user = super.getHibernateTemplate().get(User.class, phoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {

		User user = null;
		try {
			user = super.getHibernateTemplate().get(User.class, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {

		List<User> users = null;
		try {
			users = super.getHibernateTemplate().loadAll(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;

	}

	@Override
	@Transactional
	public List<User> getInactiveUsers() {

		return super.getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createQuery("from User where status = 'Inactive'", User.class).list();
	}

}
