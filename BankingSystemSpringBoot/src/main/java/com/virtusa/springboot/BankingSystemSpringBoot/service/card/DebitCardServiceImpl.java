package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.helper.UidGenerator;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCard;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.cards.DebitCardRepository;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private UidGenerator uidGenerator;

    @Autowired
    private DebitCardTypeService debitCardTypeService;

    // @Autowired
    // private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(DebitCardServiceImpl.class);

    @Override
    public DebitCard saveDebitCard(Account account) {
        DebitCard debitCard = new DebitCard();

        Long cardNumber = uidGenerator.generate16DigitCardNumber();

        while (isCardNumberTaken(cardNumber)) {
            cardNumber = uidGenerator.generate16DigitCardNumber();
        }

        debitCard.setCardNumber(cardNumber);
        debitCard.setAccount(account);
        debitCard.setCardType("Debit");
        debitCard.setIssueDate(LocalDate.now());
        debitCard.setExpiryDate(LocalDate.now().plusYears(5));
        debitCard.setCardPin(uidGenerator.generate4DigitCardPin());
        debitCard.setCvv(uidGenerator.generate3DigitCardCvv());
        debitCard.setDebitCardType(debitCardTypeService.getDebitCardType("Classic"));

        return debitCardRepository.save(debitCard);
    }

    @Override
    public boolean isCardNumberTaken(Long cardNumber) {
        return debitCardRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public void updateDebitCard(DebitCard debitCard) {
        debitCardRepository.save(debitCard);
    }

    @Override
    public void changePin(Long debitCardNumber, Integer pin) {
        DebitCard debitCard = debitCardRepository.findByCardNumber(debitCardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Debit Card", "card Number", debitCardNumber + ""));

        logger.info("Customer Verified SuccessFully, Changing Pin");
        debitCard.setCardPin(pin);
        updateDebitCard(debitCard);
    }

    // @Override
    // public List<DebitCard> getCurrentCustomerDebitCards(Long cutomerId) {
    //     List<Account> accounts = accountService.getAccountByCustomerId(cutomerId);
    //     return accounts.stream().map(a -> debitCardRepository.findByAccountAccountId(a.getAccountId()))
    //             .collect(Collectors.toList());

    // }

}
