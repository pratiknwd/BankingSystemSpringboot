package com.virtusa.springboot.BankingSystemSpringBoot.repository.cards;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCard;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {

    DebitCard findByAccountAccountId(Long accountId);

    Optional<DebitCard> findByCardNumber(Long cardNumber);

    boolean existsByCardNumber(Long cardNumber);

}
