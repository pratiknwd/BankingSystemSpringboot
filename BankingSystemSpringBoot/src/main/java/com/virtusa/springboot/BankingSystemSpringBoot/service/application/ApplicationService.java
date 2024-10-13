package com.virtusa.springboot.BankingSystemSpringBoot.service.application;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.application.Application;

public interface ApplicationService {

    public Long createApplication(Application application);

    public Application getApplicationByApplicationNumber(Long applicationNumber);

    public List<Application> getApplicationsByApplicationType(String applicationType);

    public List<Application> getApplicationByCustomerId(Long customerId);

    public List<Application> getAllPendingApplicationForApproval();

    public List<Application> getAllAccountPendingApplicationForApproval();

    public List<Application> getAllLoanPendingApplicationForApproval();

    public List<Application> getAllCreditCardPendingApplicationForApproval();

    public List<Application> getAllRejectedApplications();

    public void rejectApplication(Long applicationNumber);

    public Long approveApplicationForAccount(Long applicationNumber);

    public Long approveApplicationForLoans(Long applicationNumber);

    public Long approveApplicationForCreditCards(Long applicationNumber);

    public boolean duplicateApplicationRequest(Long customerId, String applicationType);

    public List<Application> getCurrentUserApplications();

}
