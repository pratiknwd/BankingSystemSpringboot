package com.virtusa.springboot.BankingSystemSpringBoot.mapper.Account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.AccountDetailsDTO;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.Account.CreateAccountDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.BranchMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer.CustomerMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.Account;
import com.virtusa.springboot.BankingSystemSpringBoot.model.account.AccountType;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountTypeService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.branch.BranchService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@Mapper(uses = {CustomerMapper.class,BranchMapper.class})
public abstract class AccountMapper {

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected BranchService branchService;

    @Autowired
    protected AccountTypeService acountTypeService;

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "resolveCustomerById")
    @Mapping(target = "accountType", source = "accountType", qualifiedByName = "resolveAccountType")
    @Mapping(target = "branch", source = "branchId", qualifiedByName = "resolveBranchById")
    public abstract Account toAccountEntity(CreateAccountDto createAccountDto);


    @Mapping(target = "accountType",source = "accountType.accountType")
    public abstract AccountDetailsDTO toAccountDetailsDto(Account account);

    @Named("resolveCustomerById")
    public Customer resolveCustomerById(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @Named("resolveAccountType")
    public AccountType resolveAccountType(String accountType) {
        return acountTypeService.getAccountType(accountType);
    }

    @Named("resolveBranchById")
    public Branch resolveBranchById(Long branchId) {
        return branchService.getBranchById(branchId);
    }
}
