package com.virtusa.springboot.BankingSystemSpringBoot.mapper.card;

import org.mapstruct.Mapper;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.CardDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.CreditCard;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCard;

@Mapper
public abstract class CardMapper {

    public abstract CardDto toDto(DebitCard debitCard);

    public abstract CardDto toDto(CreditCard creditCard);

}
