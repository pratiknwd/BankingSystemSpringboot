package com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.ApplicationCustomerDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.CreateCustomerDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.CustomerDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.AddressMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.UserMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

@Mapper(uses = { AddressMapper.class, UserMapper.class, AddressMapper.class })
public abstract class CustomerMapper {

    @Mapping(target = "address", source = "addressDto")
    public abstract Customer toEntity(CreateCustomerDto createCustomerDto);

    public abstract CustomerDto toDto(Customer customer);

    public abstract ApplicationCustomerDto toApplicationCustomerDto(Customer customer);
}