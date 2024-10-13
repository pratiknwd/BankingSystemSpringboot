package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.model.account.AccountType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountTypeService;

@RestController
@PreAuthorize("hasRole('Admin')")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping("/add-account-type")
    public ResponseEntity<AccountType> addAccountType(@RequestParam String accountType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountTypeService.addAccountType(accountType));
    }

    @PutMapping("/update-account-type/{accountTypeId}")
    public ResponseEntity<AccountType> updateAccountType(@PathVariable Long accountTypeId,
            @RequestParam String accountType) {

        return ResponseEntity.ok().body(accountTypeService.updAccountType(accountTypeId, accountType));
    }

    @GetMapping("/getAllAccountTypes")
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        return ResponseEntity.ok().body(accountTypeService.getAllAccountTypes());
    }

}
