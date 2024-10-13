package com.virtusa.banking.repository;

import java.util.List;

import com.virtusa.banking.model.OnlineBankingLoginDetails;

public interface OnlineBankingLoginDetailsDAO {
	void save(OnlineBankingLoginDetails details);

	List<OnlineBankingLoginDetails> findAll();

	void update(OnlineBankingLoginDetails details);

	void delete(OnlineBankingLoginDetails details);

	OnlineBankingLoginDetails findByUserId(int userId, String bankName);

	boolean hasLoginDetails(int userId, String bankName);

	OnlineBankingLoginDetails findByUserId(Integer userId);
}
