package com.virtusa.springboot.BankingSystemSpringBoot.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<List<Account>> findByAccountTypeAccountTypeId(Long accountTypeId);

	Optional<Account> findByAccountNumber(Long accountNumber);

	List<Account> findByCustomerCustomerId(Long customerId);

	boolean existsByAccountNumber(Long accountNumber);

	Optional<List<Account>> findByAccountStatus(String status);
}
