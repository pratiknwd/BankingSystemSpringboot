package com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "Loans")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Long loanId;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private Double loanAmount;

	@OneToOne
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@JoinColumn(name = "loan_type_id")
	private LoanType loanType;

	private Integer loanTerm;

	@Column(name = "loan_start_date")
	private LocalDate loanStartDate;

	@Column(name = "loan_end_date")
	private LocalDate loanEndDate;
		
}
