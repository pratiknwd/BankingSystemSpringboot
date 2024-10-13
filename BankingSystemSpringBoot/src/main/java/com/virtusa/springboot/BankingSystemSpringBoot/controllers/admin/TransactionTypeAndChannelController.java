package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionChannel;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.TransactionType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.transactions.TransactionChanelService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.transactions.TransactionTypeService;

@CrossOrigin
@RestController
public class TransactionTypeAndChannelController {

    @Autowired
    private TransactionChanelService transactionChanelService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/addTransactionType")
    public ResponseEntity<ResponseDto> addTransactionType(@RequestParam String transactionType) {
        System.out.println(transactionType);

        transactionTypeService.addTransactionType(transactionType);

        ResponseDto response = new ResponseDto();
        response.setMessage("Transaction Type : " + transactionType + " added Successfully.");

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/addTransactionChannel")
    public ResponseEntity<ResponseDto> addTransactionChannel(@RequestParam String transactionChannel) {

        transactionChanelService.addTransactionChannel(transactionChannel);

        ResponseDto response = new ResponseDto();
        response.setMessage("Transaction Channel : " + transactionChannel + " added Successfully.");

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/deleteTransactionType")
    public ResponseEntity<ResponseDto> deleteTransactionType(@RequestParam String transactionType) {

        transactionTypeService.deleteTransactionMethod(transactionType);

        ResponseDto response = new ResponseDto();
        response.setMessage("Transaction Type : " + transactionType + " deleted Successfully.");
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/deleteTransactionChannel")
    public ResponseEntity<ResponseDto> deleteTransactionChannel(@RequestParam String transactionChannel) {

        transactionChanelService.deleteTransactionChannel(transactionChannel);

        ResponseDto response = new ResponseDto();
        response.setMessage("Transaction Channel : " + transactionChannel + " deleted Successfully.");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/allTransactionTypes")
    public ResponseEntity<List<TransactionType>> getAllTransactionTypes() {
        return ResponseEntity.ok().body(transactionTypeService.getAllTransactionType());
    }

    @GetMapping("/allTransactionChannels")
    public ResponseEntity<List<TransactionChannel>> getAllTransactionChannels() {
        return ResponseEntity.ok().body(transactionChanelService.getAllTransactionChannels());
    }

}
