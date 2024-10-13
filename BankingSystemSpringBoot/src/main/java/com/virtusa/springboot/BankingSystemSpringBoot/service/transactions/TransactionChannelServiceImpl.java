package com.virtusa.springboot.BankingSystemSpringBoot.service.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionChannel;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction.TransactionChannelRepository;

@Service
public class TransactionChannelServiceImpl implements TransactionChanelService {

    @Autowired
    private TransactionChannelRepository transactionChannelRepository;

    @Override
    public TransactionChannel getTransactionalChannel(String transactionChannel) {
        return transactionChannelRepository.findByTransactionChannel(transactionChannel)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionChannel", "transactionChannel",
                        transactionChannel + ""));
    }

    @Override
    public TransactionChannel addTransactionChannel(String transactionChannel) {
        if (transactionChannelRepository.existsByTransactionChannel(transactionChannel)) {
            throw new ResourceAlreadyExistsException("TransactionChannel", "transactionChannel", transactionChannel);
        }
        TransactionChannel channel = new TransactionChannel();
        channel.setTransactionChannel(transactionChannel);

        return transactionChannelRepository.save(channel);
    }

    @Override
    public void deleteTransactionChannel(String transactionChannel) {
        TransactionChannel channel = getTransactionalChannel(transactionChannel);
        transactionChannelRepository.delete(channel);
    }

    @Override
    public List<TransactionChannel> getAllTransactionChannels() {
        return transactionChannelRepository.findAll();
    }

}
