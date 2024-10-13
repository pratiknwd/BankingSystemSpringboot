package com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionType;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    Optional<TransactionType> findByTransactionMethod(String transactionMethod);

    boolean existsByTransactionMethod(String transactionMethod);

}
