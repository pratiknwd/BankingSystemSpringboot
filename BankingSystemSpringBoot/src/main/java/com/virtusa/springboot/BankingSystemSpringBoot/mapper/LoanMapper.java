package com.virtusa.springboot.BankingSystemSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.Loan.LoanTypeDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.LoanType;

@Mapper
public abstract class LoanMapper {
    
    @Mapping(target = "loanTypeId", ignore = true)
    public abstract LoanType toLoanTypeEntity(LoanTypeDto loanTypeDto);
}
