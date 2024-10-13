package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCardType;

public interface DebitCardTypeService {

    public DebitCardType addDebitCardType(DebitCardType debitCardType);

    public void updateDebitCardType(DebitCardType debitCardType);

    public DebitCardType getDebitCardType(String debitCardType);

    public List<DebitCardType> getAllDebitCardTypes();
}
