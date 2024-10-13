package com.virtusa.springboot.BankingSystemSpringBoot.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.AccountType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.account.AccountTypeRepository;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType getAccountType(String accountType) {

        return accountTypeRepository.findByAccountType(accountType)
                .orElseThrow(() -> new ResourceNotFoundException("AccountType", "accountType", accountType));
    }

    @Override
    public AccountType addAccountType(String accountType) {
        if (accountTypeRepository.existsByAccountType(accountType)) {
            throw new ResourceAlreadyExistsException("AccountType", "account type", accountType);
        }

        AccountType accountType2 = new AccountType();
        accountType2.setAccountType(accountType);
        return accountTypeRepository.save(accountType2);
    }

    @Override
    public AccountType updAccountType(Long accountTypeId, String accountType) {
        AccountType accountType2 = accountTypeRepository.findById(accountTypeId).orElseThrow(
                () -> new ResourceNotFoundException("ApplicationType", "application type id", accountTypeId + ""));
        if (accountTypeRepository.existsByAccountType(accountType)) {
            throw new ResourceAlreadyExistsException("AccountType", "account type", accountType);
        }

        return accountTypeRepository.save(accountType2);
    }

    @Override
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

}
