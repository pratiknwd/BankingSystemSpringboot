package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction.TransactionTypeRepository;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public TransactionType addTransactionType(String transactionMethod) {
        if (transactionTypeRepository.existsByTransactionMethod(transactionMethod)) {
            throw new ResourceAlreadyExistsException("TransactionMethod", "transactionMethod", transactionMethod);
        }
        TransactionType transactionType = new TransactionType();
        transactionType.setTransactionMethod(transactionMethod);

        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public TransactionType getTransactionTypeByTransactionMethod(String transactionMethod) {
        return transactionTypeRepository.findByTransactionMethod(transactionMethod).orElseThrow(
                () -> new ResourceNotFoundException("TransactionType", "transactionMethod", transactionMethod + ""));
    }

    @Override
    public void deleteTransactionMethod(String transactionMethod) {
        TransactionType transactionType = getTransactionTypeByTransactionMethod(transactionMethod);

        transactionTypeRepository.delete(transactionType);
    }

    @Override
    public List<TransactionType> getAllTransactionType() {
        return transactionTypeRepository.findAll();
    }

}
