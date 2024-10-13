package com.virtusa.springboot.BankingSystemSpringBoot.repository.loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByLoanTypeLoanTypeId(Long loanTypeId);

}
