package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.time.LocalDate;
import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;

public interface TransactionService {

    public Transaction saveTransaction(Transaction transaction);

    public List<Transaction> getTransactionByDate(LocalDate date);

    public List<Transaction> getTransactionByAccountNumber(Long accountNumber);
    
}
