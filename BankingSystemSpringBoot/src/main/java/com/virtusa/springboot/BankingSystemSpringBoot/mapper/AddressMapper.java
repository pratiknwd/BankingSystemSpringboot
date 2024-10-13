package com.virtusa.springboot.BankingSystemSpringBoot.mapper;

import org.mapstruct.Mapper;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.demographic.AddressDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.Address;

@Mapper
public abstract class AddressMapper {
    
    public abstract Address toEntity(AddressDto addressDto);
}
