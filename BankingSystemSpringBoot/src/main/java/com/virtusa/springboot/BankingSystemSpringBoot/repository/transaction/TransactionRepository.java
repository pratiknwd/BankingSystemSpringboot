package com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTrasactionDate(LocalDate date);

    List<Transaction> findByAccountAccountNumber(Long accountNumber);

}
