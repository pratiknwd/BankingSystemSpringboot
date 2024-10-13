package com.virtusa.springboot.BankingSystemSpringBoot.service.application;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.InvalidDataException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.helper.UidGenerator;
import com.virtusa.springboot.BankingSystemSpringBoot.model.application.Application;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.application.ApplicationRepository;
import com.virtusa.springboot.BankingSystemSpringBoot.service.account.AccountService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.card.CreditCardService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UidGenerator uidGenerator;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CustomerService customerService;

    private final String pending = "Pending";

    @Override
    public Long createApplication(Application application) {

        if (!application.getCustomer().getCustomerId().equals(getLoggedInCustomer().getCustomerId())) {
            throw new InvalidDataException("Invalid Customer ID");
        }

        Long applicationNumber = uidGenerator.generate12DigitApplicationNumber();

        while (applicationRepository.existsByApplicationNumber(applicationNumber)) {
            applicationNumber = uidGenerator.generate12DigitApplicationNumber();
        }

        if (duplicateApplicationRequest(application.getCustomer().getCustomerId(),
                application.getApplicationType().getApplicationType())) {
            throw new ResourceAlreadyExistsException("Application", "ApplicationType",
                    application.getApplicationType().getApplicationType());
        }

        application.setApplicationNumber(applicationNumber);
        application.setDateOfApplication((new Date()).toString());

        applicationRepository.save(application);

        return applicationNumber;
    }

    @Override
    public Application getApplicationByApplicationNumber(Long applicationNumber) {

        return applicationRepository.findByApplicationNumber(applicationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "application", applicationNumber + ""));
    }

    @Override
    public List<Application> getApplicationsByApplicationType(String applicationType) {

        return applicationRepository.findByApplicationTypeApplicationType(applicationType);
    }

    @Override
    public List<Application> getApplicationByCustomerId(Long customerId) {

        return applicationRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public List<Application> getAllPendingApplicationForApproval() {

        return applicationRepository.findByApplicationStatus(pending);
    }

    @Override
    public List<Application> getAllRejectedApplications() {

        return applicationRepository.findByApplicationStatus("Rejected");
    }

    @Override
    public List<Application> getAllAccountPendingApplicationForApproval() {
        return Stream.of(
                getAllInvestmentAccountPendingApplications(),
                getAllSavingsAccountPendingApplications(),
                getAllCurrentAccountPendingApplications())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Application> getAllInvestmentAccountPendingApplications() {
        return applicationRepository
                .findByApplicationTypeApplicationTypeAndApplicationStatus("Investment_Account", pending);
    }

    private List<Application> getAllSavingsAccountPendingApplications() {
        return applicationRepository
                .findByApplicationTypeApplicationTypeAndApplicationStatus("Savings_Account", pending);
    }

    private List<Application> getAllCurrentAccountPendingApplications() {
        return applicationRepository
                .findByApplicationTypeApplicationTypeAndApplicationStatus("Current_Account", pending);
    }

    @Override
    public List<Application> getAllLoanPendingApplicationForApproval() {

        return applicationRepository.findByApplicationTypeApplicationTypeAndApplicationStatus("Loan", pending);
    }

    @Override
    public List<Application> getAllCreditCardPendingApplicationForApproval() {
        return applicationRepository.findByApplicationTypeApplicationTypeAndApplicationStatus("CreditCard", pending);
    }

    @Override
    public Long approveApplicationForAccount(Long applicationNumber) {

        Application application = getApplicationByApplicationNumber(applicationNumber);

        accountService.createAccount(application.getCustomer(), application.getApplicationType().getApplicationType(),
                application.getBranch());

        application.setApplicationStatus("Approved");

        applicationRepository.save(application);
        return application.getApplicationNumber();

    }

    @Override
    public Long approveApplicationForLoans(Long applicationNumber) {
        Application application = getApplicationByApplicationNumber(applicationNumber);

        application.setApplicationStatus("Approved");

        applicationRepository.save(application);

        return application.getApplicationNumber();
    }

    @Override
    public Long approveApplicationForCreditCards(Long applicationNumber) {
        Application application = getApplicationByApplicationNumber(applicationNumber);

        creditCardService.createCreditCard(application.getCustomer());

        application.setApplicationStatus("Approved");

        applicationRepository.save(application);

        return application.getApplicationNumber();
    }

    @Override
    public void rejectApplication(Long applicationNumber) {
        Application application = getApplicationByApplicationNumber(applicationNumber);
        application.setApplicationStatus("Rejected");
        applicationRepository.save(application);
    }

    @Override
    public boolean duplicateApplicationRequest(Long customerId, String applicationType) {
        return applicationRepository.existsByCustomerCustomerIdAndApplicationTypeApplicationType(customerId,
                applicationType);
    }

    private Customer getLoggedInCustomer() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerService.getCustomerByUsername(userName);
    }

    @Override
    public List<Application> getCurrentUserApplications() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.getCustomerByUsername(username);

        return getApplicationByCustomerId(customer.getCustomerId());
    }
}
