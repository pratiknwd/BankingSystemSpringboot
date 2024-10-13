package com.virtusa.springboot.BankingSystemSpringBoot.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "beneficiaries")
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beneficiary_id")
	private int beneficiaryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "beneficiary_account_aumber" )
	private Long beneficiaryAccountNumber;

	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name = "beneficiary_bank_name")
	private String beneficiaryBankName;

	@Column(name = "beneficiary_ifsc")
	private String beneficiaryIFSC;

	@Column(name = "beneficiary_status")
	private String beneficiaryStatus = "Pending";
	
	
}
