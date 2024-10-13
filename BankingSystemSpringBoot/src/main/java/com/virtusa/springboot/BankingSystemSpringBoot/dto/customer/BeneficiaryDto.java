package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import lombok.Data;

@Data
public class BeneficiaryDto {

	private int beneficiaryId;

	private CustomerDto customer;

	private Long beneficiaryAccountNumber;

	private String beneficiaryName;

	private String beneficiaryBankName;

	private String beneficiaryIFSC;

	private String beneficiaryStatus;
    
}
