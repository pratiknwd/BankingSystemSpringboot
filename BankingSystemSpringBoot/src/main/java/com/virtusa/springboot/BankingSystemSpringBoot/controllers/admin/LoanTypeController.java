package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.Loan.LoanTypeDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.LoanMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.LoanType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.Loan.LoanTypeService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin/loanType")
@PreAuthorize("hasRole('ADMIN')")
public class LoanTypeController {

    @Autowired
    private LoanTypeService loanTypeService;

    @Autowired
    private LoanMapper loanMapper;

    @GetMapping("/getAllLoanTypes")
    public ResponseEntity<List<LoanType>> getAllLoanTypes() {
        return ResponseEntity.ok(loanTypeService.getAllLoanTypes());
    }

    @PostMapping("/addLoanType")
    public ResponseEntity<LoanType> addLoanType(@Valid @RequestBody LoanTypeDto loanTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanTypeService.addLoanType(loanMapper.toLoanTypeEntity(loanTypeDto)));
    }

    @PutMapping("/updateLoanType/{loadTypeId}")
    public ResponseEntity<LoanType> updateLoanType(@PathVariable("loadTypeId") Long loadTypeId, @Valid @RequestBody LoanTypeDto loanTypeDto ){
        return ResponseEntity.ok(loanTypeService.updateLoanType(loadTypeId,loanMapper.toLoanTypeEntity(loanTypeDto)));
    }

    @DeleteMapping("/deleteLoanType/{loadTypeId}")
    public ResponseEntity<String> deleteLoanType(@PathVariable("loadTypeId") Long loadTypeId){
        loanTypeService.deleteLoanType(loadTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Loan type with id = " + loadTypeId + " successfully!!!.");
    }

}
