package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.CardDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.ChangePinDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.card.CardMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.AuthService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.card.CreditCardService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.card.DebitCardService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@CrossOrigin
@RestController
public class CardController {

    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthService authService;

    @GetMapping("/cards")
    public ResponseEntity<List<CardDto>> getDebitCards() {
        Long loggedInCustomerId = getLoggedInCustomer().getCustomerId();

        List<CardDto> cards = accountService.getAccountByCustomerId(loggedInCustomerId).stream()
                .map(acc -> cardMapper.toDto(acc.getDebitCard())).collect(Collectors.toList());

        cards.addAll(
                creditCardService.getCardByCustomerId(loggedInCustomerId)
                        .stream()
                        .map(credit -> cardMapper.toDto(credit))
                        .collect(Collectors.toList()));
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/getDebitCardNumbers")
    public ResponseEntity<List<Long>> getAllDebitCardNumbers() {
        List<Long> debitCardNumbers = accountService.getAccountByCustomerId(getLoggedInCustomer().getCustomerId())
                .stream()
                .map(acc -> acc.getDebitCard().getCardNumber()).collect(Collectors.toList());

        return ResponseEntity.ok(debitCardNumbers);
    }

    @GetMapping("/getCreditCardNumbers")
    public ResponseEntity<List<Long>> getAllCreditCardNumbers() {
        List<Long> creditCardNumbers = creditCardService.getCardByCustomerId(getLoggedInCustomer().getCustomerId())
                .stream()
                .map(debit -> debit.getCardNumber()).collect(Collectors.toList());

        return ResponseEntity.ok(creditCardNumbers);
    }

    @PutMapping("/debitCard/changePin")
    public ResponseEntity<ResponseDto> changeDebitCardPin(@RequestBody ChangePinDto changePinDto) {
        System.out.println(changePinDto);
        checkPassword(changePinDto.getPassword());
        debitCardService.changePin(changePinDto.getNumber(), changePinDto.getNewPin());
        ResponseDto responseDto = new ResponseDto("Pin Changed Successfully");
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/creditCard/changePin")
    public ResponseEntity<ResponseDto> changeCreditCardPin(@RequestBody ChangePinDto changePinDto) {
        System.out.println(changePinDto);
        checkPassword(changePinDto.getPassword());
        creditCardService.changeCreditCardPin(changePinDto.getNumber(), changePinDto.getNewPin());
        ResponseDto responseDto = new ResponseDto("Pin Changed Successfully");
        return ResponseEntity.ok().body(responseDto);
    }

    private Customer getLoggedInCustomer() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerService.getCustomerByUsername(name);
    }

    private void checkPassword(String password) {
        authService.checkPassword(getLoggedInCustomer().getUser(), password);
    }
}
