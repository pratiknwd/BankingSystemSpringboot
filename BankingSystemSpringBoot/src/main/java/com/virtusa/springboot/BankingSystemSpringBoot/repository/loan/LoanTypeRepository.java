package com.virtusa.springboot.BankingSystemSpringBoot.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.LoanType;
@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, Long>{

    Boolean existsByLoanType(String loanType);

}
