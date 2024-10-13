package com.virtusa.springboot.BankingSystemSpringBoot.repository.cards;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCardType;
@Repository
public interface DebitCardTypeRepository extends JpaRepository<DebitCardType, Long>  {
	
    Optional<DebitCardType> findByDebitCardType(String debitCardType);
}
