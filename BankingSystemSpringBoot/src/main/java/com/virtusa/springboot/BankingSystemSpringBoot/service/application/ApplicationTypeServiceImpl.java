package com.virtusa.springboot.BankingSystemSpringBoot.service.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.application.ApplicationType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.application.ApplicationTypeRepository;

@Service
public class ApplicationTypeServiceImpl implements ApplicationTypeService {

    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public ApplicationType getApplicationType(String applicationType) {
        return applicationTypeRepository.findByApplicationType(applicationType).orElseThrow(
                () -> new ResourceNotFoundException("ApplicationType", "applicationType", applicationType));
    }

    @Override
    public List<ApplicationType> getApplicationTypes() {
        return applicationTypeRepository.findAll();
    }

    @Override
    public ApplicationType addApplicationType(String applicationType) {
        if (applicationTypeRepository.existsByApplicationType(applicationType)) {
            throw new ResourceAlreadyExistsException("ApplicationType", "application type", applicationType);
        }
        ApplicationType applicationTypeEntity = new ApplicationType();
        applicationTypeEntity.setApplicationType(applicationType);
        return applicationTypeRepository.save(applicationTypeEntity);
    }

    @Override
    public ApplicationType updateApplicationType(Long applicationTypeId, String type) {
        ApplicationType applicationType = applicationTypeRepository.findById(applicationTypeId).orElseThrow(
                () -> new ResourceNotFoundException("ApplicationType", "application type id", applicationTypeId + ""));
        if (applicationTypeRepository.existsByApplicationType(type)) {
            throw new ResourceAlreadyExistsException("ApplicationType", "application type", type);
        }
        applicationType.setApplicationType(type);
        return applicationTypeRepository.save(applicationType);

    }

    @Override
    public List<String> getAllAccountApplicationType() {

        return applicationTypeRepository.findByApplicationTypeEndingWith("Account").stream()
                .map(aT -> aT.getApplicationType()).collect(Collectors.toList());

    }

    @Override
    public List<String> getAllNonAccountApplicationType() {
        return applicationTypeRepository.findAllByApplicationTypeNotEndingWith("Account").stream()
                .map(aT -> aT.getApplicationType()).collect(Collectors.toList());
    }

}
