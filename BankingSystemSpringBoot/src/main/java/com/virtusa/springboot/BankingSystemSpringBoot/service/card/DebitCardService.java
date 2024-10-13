package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCard;

public interface DebitCardService {

    public DebitCard saveDebitCard(Account account);

    public void updateDebitCard(DebitCard debitCard);

    public void changePin(Long debitCardNumber, Integer pin);

    // public List<DebitCard> getCurrentCustomerDebitCards(Long cutomerId);

    public boolean isCardNumberTaken(Long cardNumber);
}
