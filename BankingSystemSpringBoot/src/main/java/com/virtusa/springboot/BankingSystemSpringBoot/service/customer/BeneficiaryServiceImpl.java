package com.virtusa.springboot.BankingSystemSpringBoot.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.account.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public Beneficiary addBeneficiary(Beneficiary beneficiary) {
        if (beneficiaryRepository.existsByBeneficiaryAccountNumberAndCustomerCustomerId(
                beneficiary.getBeneficiaryAccountNumber(), beneficiary.getCustomer().getCustomerId())) {
            throw new ResourceAlreadyExistsException("Beneficiary", "acccount number",
                    beneficiary.getBeneficiaryAccountNumber() + "");
        }
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public void deleteBeneficiary(Integer beneficiaryId) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).get();

        beneficiaryRepository.delete(beneficiary);

    }

    @Override
    public void updateBeneficiary(Beneficiary beneficiary) {
        beneficiaryRepository.save(beneficiary);
    }

    @Override
    public List<Beneficiary> getBeneficiaryByCustomerId(Long customerId) {
        return beneficiaryRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public void approveBeneficiary(Integer beneficiaryId) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).get();

        beneficiary.setBeneficiaryStatus("Approved");

        Customer customer = beneficiary.getCustomer();
        customer.getBeneficiaries().add(beneficiary);

        customerService.updateCustomer(customer);

        updateBeneficiary(beneficiary);
    }

    @Override
    public List<Beneficiary> getAllBeneficiaryPendingForApproval() {
        return beneficiaryRepository.findByBeneficiaryStatus("Pending");
    }

    @Override
    public void rejectedBeneficiary(Integer beneficiaryId) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiary", "beneficiaryid", beneficiaryId + ""));
        beneficiary.setBeneficiaryStatus("Rejected");

        updateBeneficiary(beneficiary);
    }

    public List<Beneficiary> getAllRejectedBeneficiary() {
        return beneficiaryRepository.findByBeneficiaryStatus("Rejected");
    }

}
