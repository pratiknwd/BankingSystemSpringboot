package com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Loan_Types")
public class LoanType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_type_id")
	private Long loanTypeId;

	@Column(name = "loan_interest")
	private Float loanInterest;

	@Column(name = "loan_type")
	private String loanType;

}
