package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.helper.UidGenerator;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.CreditCard;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.cards.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private UidGenerator uidGenerator;

    @Autowired
    private CreditCardRepository creditCardRepository;


    private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Override
    public List<CreditCard> getCardByCustomerId(Long customerId) {

        return creditCardRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public CreditCard createCreditCard(Customer customer) {

        CreditCard creditCard = new CreditCard();
        Long cardNumber = uidGenerator.generate16DigitCardNumber();

        while (isCardNumberTaken(cardNumber)) {
            cardNumber = uidGenerator.generate16DigitCardNumber();
        }

        creditCard.setCardNumber(cardNumber);
        creditCard.setCardType("Credit");
        creditCard.setIssueDate(LocalDate.now());
        creditCard.setExpiryDate(LocalDate.now().plusYears(5));
        creditCard.setCardPin(uidGenerator.generate4DigitCardPin());
        creditCard.setCvv(uidGenerator.generate3DigitCardCvv());
        creditCard.setCustomer(customer);
        creditCard.setCreditLimit(100000.0);

        return creditCardRepository.save(creditCard);

    }

    private boolean isCardNumberTaken(Long cardNumber) {
        return creditCardRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public void deleteCreditCard(Long creditCardNumber) {
        CreditCard creditCard = getCreditCardByCardNumber(creditCardNumber);
        creditCardRepository.delete(creditCard);
    }

    @Override
    public CreditCard getCreditCardByCardNumber(Long creditCardNumber) {
        return creditCardRepository.findByCardNumber(creditCardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("CrediCard", "cardNumber", creditCardNumber + ""));
    }

    @Override
    public void changeCreditCardPin(Long cardNumber, Integer pin) {
        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card", "cardNumber", cardNumber + ""));

        logger.info("Customer Verified, changing credit card Pin");

        creditCard.setCardPin(pin);
        creditCardRepository.save(creditCard);
    }
}
