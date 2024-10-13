package com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.AtmTransaction;
@Repository
public interface AtmTransactionRepository extends JpaRepository<AtmTransaction, Long> {
	
}
