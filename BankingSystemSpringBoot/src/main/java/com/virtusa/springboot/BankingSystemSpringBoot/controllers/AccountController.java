package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AccountDetailsDTO;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AddBalanceDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.ChangePinDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.Account.AccountMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.AuthService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDetailsDTO> getAccountByAccountNumber(@PathVariable Long accountNumber) {

        AccountDetailsDTO accountDetailsDto = accountMapper
                .toAccountDetailsDto(accountService.getAccountByAccountNumber(accountNumber));

        return ResponseEntity.ok().body(accountDetailsDto);
    }

    @GetMapping("/account-details")
    public ResponseEntity<List<AccountDetailsDTO>> getAccountDetails() {

        Customer customer = getLoggedInCustomer();

        List<AccountDetailsDTO> accounts = accountService.getAccountByCustomerId(customer.getCustomerId()).stream()
                .map(a -> accountMapper.toAccountDetailsDto(a)).collect(Collectors.toList());

        return ResponseEntity.ok().body(accounts);
    }

    @PutMapping("/addBalance")
    public ResponseEntity<ResponseDto> addBalance(@RequestBody AddBalanceDto addBalanceDto) {

        boolean added = accountService.addBalance(addBalanceDto);
        ResponseDto response = new ResponseDto();

        if (added) {
            response.setMessage("Balance updated");
            return ResponseEntity.ok().body(response);
        }
        response.setMessage("Transaction Denied because the Account with accountNumber : "
                + addBalanceDto.getAccountNumber() + " is blocked...!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/getAccountNumbers")
    public ResponseEntity<List<Long>> getCurrentUserAccountNumbers() {
        List<Long> accountNumbers = accountService.getAccountByCustomerId(getLoggedInCustomer().getCustomerId())
                .stream()
                .map(acc -> acc.getAccountNumber()).collect(Collectors.toList());

        return ResponseEntity.ok(accountNumbers);
    }

    @PutMapping("/changeTpin")
    public ResponseEntity<ResponseDto> changeDebitCardPin(@RequestBody ChangePinDto changePinDto) {
        checkPassword(changePinDto.getPassword());
        accountService.updateTPin(changePinDto.getNumber(), changePinDto.getNewPin());
        ResponseDto responseDto = new ResponseDto("Pin Changed Successfully");
        return ResponseEntity.ok().body(responseDto);
    }

    public Customer getLoggedInCustomer() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return customerService.getCustomerByUsername(username);
    }

    private void checkPassword(String password) {
        authService.checkPassword(getLoggedInCustomer().getUser(), password);
    }
}
