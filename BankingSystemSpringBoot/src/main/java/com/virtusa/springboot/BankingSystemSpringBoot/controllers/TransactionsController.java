package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions.CreateTransactionDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.transactions.TransactionDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.transactions.TransactionMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;
import com.virtusa.springboot.BankingSystemSpringBoot.service.transactions.TransactionService;

@CrossOrigin
@RestController
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    @PostMapping("/transfer_funds")
    public ResponseEntity<ResponseDto> transferFunds(@RequestBody CreateTransactionDto createTransactionDto) {

        Integer tPin = createTransactionDto.getTPin();
        Transaction transaction = transactionMapper.toEntity(createTransactionDto);
        ResponseDto response = new ResponseDto();

        if (!transaction.getAccount().getTpin().equals(tPin)) {
            response.setMessage("Invalid TPIN...!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (transaction.getAccount().getAccountStatus().equalsIgnoreCase("Active")) {
            transactionService.saveTransaction(transaction);
            response.setMessage("Transfer SuccessFull...!");
            return ResponseEntity.ok().body(response);
        }
        response.setMessage("Transaction Denied because the Account with accountNumber : "
                + transaction.getAccount().getAccountNumber() + " is blocked...!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @GetMapping("/show_transactions")
    public ResponseEntity<List<TransactionDto>> getMethodName(@RequestParam Long accountNumber) {
        System.out.println(accountNumber);

        List<TransactionDto> transactions = transactionService.getTransactionByAccountNumber(accountNumber).stream()
                .map(t -> transactionMapper.toDto(t))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(transactions);
    }

}
