package com.virtusa.springboot.BankingSystemSpringBoot.dto.branch;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddBranchDto {

    @NotBlank(message = "Branch Ifsc code cannot be empty or null.")
	@Size(min = 11, max = 11, message = "Branch Ifsc should be of exactly 11 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Ifsc should contain only alpha numerics")
	private String ifsc;
    
	private AddressDto addressDto;

}
