package com.virtusa.springboot.BankingSystemSpringBoot.service.Loan;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.LoanType;

public interface LoanTypeService {

    List<LoanType> getAllLoanTypes();

    LoanType addLoanType(LoanType loanTypeEntity);

    LoanType updateLoanType(Long loadTypeId, LoanType loanTypeEntity);

    void deleteLoanType(Long loadTypeId);

}
