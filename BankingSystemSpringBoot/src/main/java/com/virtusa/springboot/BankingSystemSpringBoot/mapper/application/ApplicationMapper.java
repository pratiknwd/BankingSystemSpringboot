package com.virtusa.springboot.BankingSystemSpringBoot.mapper.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.AccountApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.ApplicationDetailsDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.ApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.PendingAccountApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.PendingApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer.CustomerMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.application.Application;
import com.virtusa.springboot.BankingSystemSpringBoot.model.application.ApplicationType;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.application.ApplicationTypeService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.branch.BranchService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@Mapper(uses = { CustomerMapper.class })
public abstract class ApplicationMapper {

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected ApplicationTypeService applicationTypeService;

    @Autowired
    protected BranchService branchService;

    @Mapping(target = "branchIfsc", source = "branch.ifsc")
    @Mapping(target = "applicationType", source = "applicationType.applicationType")
    public abstract PendingAccountApplicationDto toPendingAccountApplicationDto(Application application);

    @Mapping(target = "applicationType", source = "applicationType.applicationType")
    public abstract PendingApplicationDto toPendingApplicationDto(Application application);

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "resolveCustomer")
    @Mapping(target = "applicationType", source = "applicationType", qualifiedByName = "resolveApplicationType")
    @Mapping(target = "branch", source = "ifsc", qualifiedByName = "resolveBranch")
    public abstract Application toEntity(AccountApplicationDto accountApplicationDto);

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "resolveCustomer")
    @Mapping(target = "applicationType", source = "applicationType", qualifiedByName = "resolveApplicationType")
    public abstract Application toEntity(ApplicationDto applicationDto);

    @Mapping(target = "applicationType", source = "applicationType.applicationType")
    @Mapping(target = "ifsc", source = "branch.ifsc")
    public abstract ApplicationDetailsDto toApplicationDetailsDto(Application application);

    @Named("resolveCustomer")
    public Customer resolveCustomer(Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @Named("resolveApplicationType")
    public ApplicationType resolveApplicationType(String applicationType) {
        return applicationTypeService.getApplicationType(applicationType);
    }

    @Named("resolveBranch")
    public Branch resolveBranch(String ifsc) {
        return branchService.getBranchByIfsc(ifsc);
    }

}
