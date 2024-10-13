package com.virtusa.springboot.BankingSystemSpringBoot.service.application;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.application.ApplicationType;

public interface ApplicationTypeService {

    public ApplicationType getApplicationType(String applicationType);

    public List<ApplicationType> getApplicationTypes();

    public ApplicationType addApplicationType(String applicationType);

    public ApplicationType updateApplicationType(Long applicationTypeId, String type);

    public List<String> getAllAccountApplicationType();

    public List<String> getAllNonAccountApplicationType();
    
}
