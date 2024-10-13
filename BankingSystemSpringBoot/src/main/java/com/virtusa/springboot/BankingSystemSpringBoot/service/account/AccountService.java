package com.virtusa.springboot.BankingSystemSpringBoot.service.account;

import java.util.List;
import java.util.Optional;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AddBalanceDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;

public interface AccountService {

    public Account createAccount(Customer customer,String accountType,Branch branch);

    public Account getAccountByAccountNumber(Long accountNumber);

    public Optional<Account> getAccountByAccountId(Long id);

    public List<Account> getAccountByCustomerId(Long customerId);

    public Branch getBranchByAccountNumber(Long id);

    public void updateAccount(Account account);

    public boolean isAccountNumberPresent(Long accountNumber);

    public void setTransactions(Transaction transaction,Account account);

    public void updateTPin(Long accountNumber, Integer tPin);

    public List<Account> getAllAccountsPendingForApproval();

    public List<Account> getAllBlockedAccounts();

    public boolean addBalance(AddBalanceDto addBalanceDto);
}
