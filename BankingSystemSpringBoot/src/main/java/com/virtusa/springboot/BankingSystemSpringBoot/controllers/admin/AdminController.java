package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AccountDetailsDTO;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.PendingAccountApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.PendingApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.BeneficiaryDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.Account.AccountMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.application.ApplicationMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer.BeneficiaryMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.application.ApplicationService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.BeneficiaryService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<AccountDetailsDTO> getAccountByAccountNumber(@PathVariable Long accountNumber) {

        AccountDetailsDTO accountDetailsDto = accountMapper
                .toAccountDetailsDto(accountService.getAccountByAccountNumber(accountNumber));

        return ResponseEntity.ok().body(accountDetailsDto);
    }

    @PutMapping("/{accountNumber}/change-account-status-blocked")
    public ResponseEntity<ResponseDto> changeAccountStatusToBlock(@PathVariable Long accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        account.setAccountStatus("Blocked");
        accountService.updateAccount(account);

        ResponseDto response = new ResponseDto();
        response.setMessage("Account Status for accountNumber : " + account.getAccountNumber()
                + " has been successfully changed to blocked");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{accountNumber}/change-account-status-active")
    public ResponseEntity<ResponseDto> changeAccountStatusToActive(@PathVariable Long accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        account.setAccountStatus("Active");
        accountService.updateAccount(account);

        ResponseDto response = new ResponseDto();
        response.setMessage("Account Status for accountNumber : " + account.getAccountNumber()
                + " has been successfully changed to Active");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/accounts-pending-applications")
    public ResponseEntity<List<PendingAccountApplicationDto>> accountsApplicationsPendingForApproval() {
        List<PendingAccountApplicationDto> pendingList = applicationService.getAllAccountPendingApplicationForApproval()
                .stream().map(a -> applicationMapper.toPendingAccountApplicationDto(a)).collect(Collectors.toList());

        return ResponseEntity.ok().body(pendingList);
    }

    @GetMapping("/loans-pending-applications")
    public ResponseEntity<List<PendingApplicationDto>> loanApplicationsPendingForApproval() {

        List<PendingApplicationDto> pendingLoanApplication = applicationService
                .getAllLoanPendingApplicationForApproval().stream()
                .map(a -> applicationMapper.toPendingApplicationDto(a)).collect(Collectors.toList());

        return ResponseEntity.ok().body(pendingLoanApplication);
    }

    @GetMapping("/creditCards-pending-applications")
    public ResponseEntity<List<PendingApplicationDto>> creditCardApplicationsPendingForApproval() {
        List<PendingApplicationDto> pendingCreditCardApplication = applicationService
                .getAllCreditCardPendingApplicationForApproval().stream()
                .map(a -> applicationMapper.toPendingApplicationDto(a)).collect(Collectors.toList());
        return ResponseEntity.ok().body(pendingCreditCardApplication);
    }

    @PutMapping("/approve-account/{applicationNumber}")
    public ResponseEntity<ResponseDto> approveApplicationForAccount(
            @PathVariable Long applicationNumber) {

        System.out.println(applicationNumber);

        applicationService.approveApplicationForAccount(applicationNumber);
        ResponseDto response = new ResponseDto();
        response.setMessage(
                "Account application with application number : " + applicationNumber + " successfully approved.");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/approve-loan/{applicationNumber}")
    public ResponseEntity<ResponseDto> approveApplicationForLoan(
            @PathVariable("applicationNumber") Long applicationNumber) {

        applicationService.approveApplicationForLoans(applicationNumber);
        ResponseDto response = new ResponseDto();
        response.setMessage(
                "Loan application with application number : " + applicationNumber + " successfully approved.");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/approve-credit-cards/{applicationNumber}")
    public ResponseEntity<ResponseDto> approveApplicationForCreditCard(
            @PathVariable("applicationNumber") Long applicationNumber) {

        applicationService.approveApplicationForCreditCards(applicationNumber);

        ResponseDto response = new ResponseDto();
        response.setMessage("Credit card application with application number : " + applicationNumber
                + " successfully approved.");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reject/{applicationNumber}")
    public ResponseEntity<ResponseDto> rejectApplication(@PathVariable Long applicationNumber) {

        applicationService.rejectApplication(applicationNumber);
        ResponseDto response = new ResponseDto();
        response.setMessage("Application with application number : " + applicationNumber + " rejected.");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/blocked-accounts")
    public ResponseEntity<List<AccountDetailsDTO>> getAllBloackedAccounts() {

        List<AccountDetailsDTO> blockedAccounts = accountService.getAllBlockedAccounts().stream()
                .map(acc -> accountMapper.toAccountDetailsDto(acc)).collect(Collectors.toList());

        return ResponseEntity.ok().body(blockedAccounts);
    }

    @GetMapping("/rejected-application")
    public ResponseEntity<List<PendingApplicationDto>> getAllRejectedApplications() {

        List<PendingApplicationDto> rejectedApplications = applicationService.getAllRejectedApplications().stream()
                .map(a -> applicationMapper.toPendingAccountApplicationDto(a)).collect(Collectors.toList());
        return ResponseEntity.ok().body(rejectedApplications);
    }

    @GetMapping("/beneficiaries_pending_for_approval")
    public ResponseEntity<List<BeneficiaryDto>> getAllBeneficiaryPendingForApproval() {
        List<BeneficiaryDto> pendingBeneficiary = beneficiaryService.getAllBeneficiaryPendingForApproval().stream()
                .map(beneficiary -> beneficiaryMapper.toDto(beneficiary)).collect(Collectors.toList());

        return ResponseEntity.ok().body(pendingBeneficiary);
    }

    @PutMapping("/approve_beneficiary/{beneficiaryId}")
    public ResponseEntity<ResponseDto> approveBeneficiary(@PathVariable Integer beneficiaryId) {
        beneficiaryService.approveBeneficiary(beneficiaryId);
        ResponseDto response = new ResponseDto();
        response.setMessage("Beneficiary with id : " + beneficiaryId + " successfully approved");

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reject_beneficiary/{beneficiaryId}")
    public ResponseEntity<ResponseDto> rejectBeneficiary(@PathVariable Integer beneficiaryId) {
        beneficiaryService.rejectedBeneficiary(beneficiaryId);
        ResponseDto response = new ResponseDto();
        response.setMessage("Beneficiary with id : " + beneficiaryId + " rejected.");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/beneficiaries-rejected-application")
    public ResponseEntity<List<BeneficiaryDto>> getAllBeneficiaryRejectedApplications() {

        List<BeneficiaryDto> rejected = beneficiaryService.getAllRejectedBeneficiary().stream()
                .map(beneficiary -> beneficiaryMapper.toDto(beneficiary)).collect(Collectors.toList());

        return ResponseEntity.ok().body(rejected);
    }

}
