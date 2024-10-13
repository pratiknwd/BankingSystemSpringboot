package com.virtusa.springboot.BankingSystemSpringBoot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.branch.AddBranchDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.branch.BranchDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;

@Mapper(uses = AddressMapper.class)
public abstract class BranchMapper {

    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "address", source = "addressDto")
    public abstract Branch toEntity(AddBranchDto addBranchDto);

    public abstract BranchDto toDto(Branch branch);

}
