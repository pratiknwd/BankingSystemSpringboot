package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.card.CreditCard;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

public interface CreditCardService {

    public List<CreditCard> getCardByCustomerId(Long customerId);

    public CreditCard createCreditCard(Customer customer);

    public void deleteCreditCard(Long creditCardNumber);

    public CreditCard getCreditCardByCardNumber(Long creditCardNumber);

    public void changeCreditCardPin(Long cardNumber, Integer pin);

}
