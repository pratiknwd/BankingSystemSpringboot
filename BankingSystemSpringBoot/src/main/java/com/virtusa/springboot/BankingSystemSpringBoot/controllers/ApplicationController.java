package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.AccountApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.ApplicationDetailsDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.application.ApplicationDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.application.ApplicationMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.application.Application;
import com.virtusa.springboot.BankingSystemSpringBoot.service.application.ApplicationService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.application.ApplicationTypeService;

@CrossOrigin
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationTypeService applicationTypeService;

    @Autowired
    private ApplicationMapper applicationMapper;

    @PostMapping("/account-creation-application")
    public ResponseEntity<ResponseDto> createAccountApplication(
            @RequestBody AccountApplicationDto accountApplicationDto) {
        System.out.println(accountApplicationDto);

        Application application = applicationMapper.toEntity(accountApplicationDto);

        Long applicationNumber = applicationService.createApplication(application);
        ResponseDto response = new ResponseDto();
        response.setMessage("Your request for " + accountApplicationDto.getApplicationType()
                + " opening has been successfully received with application number : "
                + applicationNumber + ". Kindly wait for approval.");

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/apply")
    public ResponseEntity<ResponseDto> applyForFinancialTools(@RequestBody ApplicationDto applicationDto) {

        Application application = applicationMapper.toEntity(applicationDto);

        Long applicationNumber = applicationService.createApplication(application);
        ResponseDto response = new ResponseDto();
        response.setMessage("Your request has been successfully received with application number : "
                + applicationNumber + ". Kindly wait for approval.");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/account-application-type")
    public ResponseEntity<List<String>> getAccountApplicationType() {
        return ResponseEntity.ok().body(applicationTypeService.getAllAccountApplicationType());
    }

    @GetMapping("/financial-application-type")
    public ResponseEntity<List<String>> getFinancialApplicationType() {
        return ResponseEntity.ok().body(applicationTypeService.getAllNonAccountApplicationType());
    }

    @GetMapping("/getCurrentUserApplications")
    public ResponseEntity<List<ApplicationDetailsDto>> getAllCurrentUserApplications() {

        List<ApplicationDetailsDto> applications = applicationService.getCurrentUserApplications().stream()
                .map(application -> applicationMapper.toApplicationDetailsDto(application))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(applications);
    }

}
