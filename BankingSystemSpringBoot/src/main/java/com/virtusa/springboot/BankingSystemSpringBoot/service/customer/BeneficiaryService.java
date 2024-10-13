package com.virtusa.springboot.BankingSystemSpringBoot.service.customer;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;

public interface BeneficiaryService {

    public Beneficiary addBeneficiary(Beneficiary beneficiary);

    public void updateBeneficiary(Beneficiary beneficiary);

    public void deleteBeneficiary(Integer beneficiaryId);

    public List<Beneficiary> getBeneficiaryByCustomerId(Long customerId);

    public void approveBeneficiary(Integer beneficiaryId);

    public void rejectedBeneficiary(Integer beneficiaryId);

    public List<Beneficiary> getAllBeneficiaryPendingForApproval();

    public List<Beneficiary> getAllRejectedBeneficiary();

}
