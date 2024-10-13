package com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.AddBeneficiaryDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.BeneficiaryDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@Mapper(uses = CustomerMapper.class)
public abstract class BeneficiaryMapper {

    @Autowired
    protected CustomerService customerService;

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "resolveCustomer")
    public abstract Beneficiary toEntity(AddBeneficiaryDto addBeneficiaryDto);

    public abstract BeneficiaryDto toDto(Beneficiary beneficiary);

    @Named("resolveCustomer")
    public Customer resolveCustomer(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

}
