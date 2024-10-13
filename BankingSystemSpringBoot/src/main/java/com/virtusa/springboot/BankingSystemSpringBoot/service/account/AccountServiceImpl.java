package com.virtusa.springboot.BankingSystemSpringBoot.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AddBalanceDto;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.helper.UidGenerator;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.account.AccountRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.service.card.DebitCardService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public Account createAccount(Customer customer, String accountType, Branch branch) {

        Account account = new Account();

        Long accNum = uidGenerator.generate14DigitAccountNumber();
        while (isAccountNumberPresent(accNum)) {
            accNum = uidGenerator.generate14DigitAccountNumber();
        }
        account.setCustomer(customer);
        account.setAccountNumber(accNum);
        account.setTpin(uidGenerator.generate6DigitTPin());

        account.setAccountType(accountTypeService.getAccountType(accountType));
        account.setBalance(0.0);
        account.setBranch(branch);

        accountRepository.save(account);

        account.setDebitCard(debitCardService.saveDebitCard(account));

        return account;
    }

    @Override
    public Account getAccountByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber + ""));
    }

    @Override
    public Optional<Account> getAccountByAccountId(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Branch getBranchByAccountNumber(Long id) {
        return getAccountByAccountNumber(id).getBranch();
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean isAccountNumberPresent(Long accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public void setTransactions(Transaction transaction, Account account) {
        List<Transaction> list = account.getTransactions();

        list.add(transaction);

        updateAccount(account);
    }

    @Override
    public List<Account> getAccountByCustomerId(Long customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public void updateTPin(Long accountNumber, Integer tPin) {
        Account account = getAccountByAccountNumber(accountNumber);
        account.setTpin(tPin);
        updateAccount(account);
    }

    @Override
    public List<Account> getAllAccountsPendingForApproval() {
        return accountRepository.findByAccountStatus("Pending").get();
    }

    @Override
    public List<Account> getAllBlockedAccounts() {
        return accountRepository.findByAccountStatus("Blocked").get();
    }

    @Override
    public boolean addBalance(AddBalanceDto addBalanceDto) {
        if (!isAccountNumberPresent(addBalanceDto.getAccountNumber())) {
            throw new ResourceNotFoundException("Account", "account number", addBalanceDto.getAccountNumber() + "");
        }
        boolean added = false;

        Account account = getAccountByAccountNumber(addBalanceDto.getAccountNumber());

        if (account.getAccountStatus().equalsIgnoreCase("Blocked")) {
            return added;
        }
        account.setBalance(account.getBalance() + addBalanceDto.getAmount());
        updateAccount(account);
        added = true;
        
        return added;
    }
}
