package com.virtusa.springboot.BankingSystemSpringBoot.dto.branch;

import java.time.LocalDate;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    
    private Long branchId;

    private AddressDto address;

    private String ifsc;

    private LocalDate createdDate;
}
