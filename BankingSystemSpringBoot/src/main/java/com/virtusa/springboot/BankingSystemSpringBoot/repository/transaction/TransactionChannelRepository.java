package com.virtusa.springboot.BankingSystemSpringBoot.repository.transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionChannel;

@Repository
public interface TransactionChannelRepository extends JpaRepository<TransactionChannel, Long> {

    Optional<TransactionChannel> findByTransactionChannel(String transactionChannel);

    boolean existsByTransactionChannel(String transactionChannel);

}
