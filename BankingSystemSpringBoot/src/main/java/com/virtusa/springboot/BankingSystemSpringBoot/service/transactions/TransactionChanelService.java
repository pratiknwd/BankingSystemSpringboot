package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionChannel;

public interface TransactionChanelService {

    public TransactionChannel addTransactionChannel(String transactionChannel);

    public void deleteTransactionChannel(String transactionChannel);

    public TransactionChannel getTransactionalChannel(String transactionChannel);

    public List<TransactionChannel> getAllTransactionChannels();
}
