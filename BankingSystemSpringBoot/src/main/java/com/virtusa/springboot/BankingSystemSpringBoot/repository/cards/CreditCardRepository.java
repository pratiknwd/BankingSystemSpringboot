package com.virtusa.springboot.BankingSystemSpringBoot.repository.cards;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.card.CreditCard;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByCustomerCustomerId(Long customerId);

    Optional<CreditCard> findByCardNumber(Long cardNumber);

    boolean existsByCardNumber(Long cardNumber);

}
