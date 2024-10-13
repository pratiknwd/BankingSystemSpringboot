package com.virtusa.springboot.BankingSystemSpringBoot.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBeneficiaryDto {

	private Long customerId;

    @NotNull(message = "beneficiary account cannot be empty")
	private Long beneficiaryAccountNumber;

    @NotBlank(message = "beneficiary name cannot be empty")
	private String beneficiaryName;

    @NotBlank(message = "beneficiary Bank Name cannot be empty")
	private String beneficiaryBankName;

    @NotBlank(message = "beneficiary IFSC cannot be empty")
	private String beneficiaryIFSC;

}
