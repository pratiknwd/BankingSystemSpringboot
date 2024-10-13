package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionType;

public interface TransactionTypeService {

    public TransactionType addTransactionType(String transactionMethod);

    public void deleteTransactionMethod(String transactionMethod);

    public TransactionType getTransactionTypeByTransactionMethod(String transactionMethod);

    public List<TransactionType> getAllTransactionType();

}
