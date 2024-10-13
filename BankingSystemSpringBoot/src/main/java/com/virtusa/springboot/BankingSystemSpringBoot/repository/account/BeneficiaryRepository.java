package com.virtusa.springboot.BankingSystemSpringBoot.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

    public List<Beneficiary> findByCustomerCustomerId(Long customerId);

    public List<Beneficiary> findByBeneficiaryStatus(String status);

    boolean existsByBeneficiaryAccountNumber(Long accountNumber);

    boolean existsByBeneficiaryAccountNumberAndCustomerCustomerId(Long accountNumber, Long customerId);
}
