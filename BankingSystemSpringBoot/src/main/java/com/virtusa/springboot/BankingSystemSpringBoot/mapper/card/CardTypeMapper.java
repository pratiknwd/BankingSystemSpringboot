package com.virtusa.springboot.BankingSystemSpringBoot.mapper.card;

import org.mapstruct.Mapper;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.DebitCardTypeDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCardType;

@Mapper
public abstract class CardTypeMapper {

    public abstract DebitCardType toEntity(DebitCardTypeDto debitCardTypeDto);

    public abstract DebitCardTypeDto toDto(DebitCardType debitCardType);

}
