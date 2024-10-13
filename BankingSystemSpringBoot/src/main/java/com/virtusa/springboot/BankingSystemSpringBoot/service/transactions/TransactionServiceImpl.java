package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.InsufficientFundException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction.TransactionRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        if (transaction.getAmount() > transaction.getAccount().getBalance()) {
            throw new InsufficientFundException(transaction.getAccount().getAccountNumber());
        }

        if (!isValidBeneficiary(transaction)) {
            throw new ResourceNotFoundException("Beneficiary", "account number ",
                    transaction.getAccociatedAccountNumber() + "");
        }

        deductBalance(transaction.getAccount(), transaction.getAmount());

        addBalanceIfAccountPresentInDb(transaction);

        transaction.setTransferDesciption("Amount : " + transaction.getAmount() + " debited from "
                + transaction.getAccount().getAccountNumber() + " to " + transaction.getAccociatedAccountNumber()
                + " on " + LocalDateTime.now() + ". Transaction Type :"
                + transaction.getTransactionType().getTransactionMethod() + ", TransactionChannel : "
                + transaction.getTransactionChannel().getTransactionChannel());
        transaction.setTrasactionDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByDate(LocalDate date) {
        return transactionRepository.findByTrasactionDate(date);
    }

    @Override
    public List<Transaction> getTransactionByAccountNumber(Long accountNumber) {
        return transactionRepository.findByAccountAccountNumber(accountNumber);
    }

    private boolean isValidBeneficiary(Transaction transaction) {
        List<Beneficiary> beneficiaries = transaction.getAccount().getCustomer().getBeneficiaries();

        return beneficiaries.stream()
                .anyMatch(b -> b.getBeneficiaryAccountNumber().equals(transaction.getAccociatedAccountNumber())
                        && b.getBeneficiaryStatus().equalsIgnoreCase("Approved"));
    }

    private void deductBalance(Account account, Double amount) {
        Double updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);
        accountService.updateAccount(account);
    }

    private void addBalanceIfAccountPresentInDb(Transaction transaction) {
        Long accountNumber = transaction.getAccociatedAccountNumber();
        if (!accountService.isAccountNumberPresent(accountNumber)) {
            return;
        }

        Account creditAccount = accountService.getAccountByAccountNumber(accountNumber);
        createCreditTransaction(transaction, creditAccount);
        creditAccount.setBalance(creditAccount.getBalance() + transaction.getAmount());
        accountService.updateAccount(creditAccount);
    }

    private void createCreditTransaction(Transaction transaction, Account account) {
        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account);
        transaction2.setAccociatedAccountNumber(transaction.getAccount().getAccountNumber());
        transaction2.setAmount(transaction.getAmount());
        transaction2.setRemarks(transaction.getRemarks());
        transaction2.setTransferDesciption("Amount : " + transaction.getAmount() + " credited to "
                + account.getAccountNumber() + " from " + transaction.getAccount().getAccountNumber()
                + " on " + LocalDate.now() + ".  Transaction Type :"
                + transaction.getTransactionType().getTransactionMethod() + ", TransactionChannel : "
                + transaction.getTransactionChannel().getTransactionChannel());

        transaction2.setTrasactionDate(LocalDate.now());
        transaction2.setTransactionType(transaction.getTransactionType());
        transaction2.setTransactionChannel(transaction.getTransactionChannel());

        transactionRepository.save(transaction2);
    }

}
