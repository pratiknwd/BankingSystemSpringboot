package com.virtusa.springboot.BankingSystemSpringBoot.service.account;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.AccountType;

public interface AccountTypeService {

    AccountType getAccountType(String accountType);

    AccountType addAccountType(String accountType);

    AccountType updAccountType(Long accountTypeId, String accountType);

    List<AccountType> getAllAccountTypes();

}
