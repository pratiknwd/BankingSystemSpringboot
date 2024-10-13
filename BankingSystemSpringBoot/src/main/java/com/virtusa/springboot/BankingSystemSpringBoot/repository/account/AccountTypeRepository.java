package com.virtusa.springboot.BankingSystemSpringBoot.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
	
    Optional<AccountType> findByAccountType(String accountType);

    boolean existsByAccountType(String accountType);
}
