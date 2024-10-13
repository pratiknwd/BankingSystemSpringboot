package com.virtusa.springboot.BankingSystemSpringBoot.mapper.transactions;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions.CreateTransactionDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions.TransactionDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionChannel;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.transactions.TransactionChanelService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.transactions.TransactionTypeService;

@Mapper
public abstract class TransactionMapper {

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected TransactionTypeService transactionTypeService;

    @Autowired
    protected TransactionChanelService transactionChanelService;

    @Mapping(target = "account", source = "accountNumber", qualifiedByName = "resolveAccount")
    @Mapping(target = "accociatedAccountNumber",source = "beneficiaryAccountNumber")
    @Mapping(target = "transactionType", source = "transactionType", qualifiedByName = "resolveTransactionType")
    @Mapping(target = "transactionChannel", source = "transactionChannel", qualifiedByName = "resolveTransactionChannel")
    public abstract Transaction toEntity(CreateTransactionDto createTransactionDto);

    @Mapping(target = "account", source = "account.accountNumber")
    @Mapping(target = "transactionType", source = "transactionType.transactionMethod")
    @Mapping(target = "transactionChannel", source = "transactionChannel.transactionChannel")
    public abstract TransactionDto toDto(Transaction transaction);

    @Named("resolveAccount")
    public Account resolveAccount(Long accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @Named("resolveTransactionType")
    public TransactionType resolveTransactionType(String transactionType) {
        return transactionTypeService.getTransactionTypeByTransactionMethod(transactionType);
    }

    @Named("resolveTransactionChannel")
    public TransactionChannel resolveTransactionChannel(String transactionalChannel) {
        return transactionChanelService.getTransactionalChannel(transactionalChannel);
    }
}
