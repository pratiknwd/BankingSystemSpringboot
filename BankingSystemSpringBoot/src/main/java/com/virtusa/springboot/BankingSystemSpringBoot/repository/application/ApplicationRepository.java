package com.virtusa.springboot.BankingSystemSpringBoot.repository.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.application.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	Optional<Application> findByApplicationNumber(Long applicationNumber);

	List<Application> findByCustomerCustomerId(Long customerId);

	List<Application> findByApplicationTypeApplicationTypeId(Long applicationTypeId);

	List<Application> findByApplicationTypeApplicationType(String applicationType);

	List<Application> findByApplicationTypeApplicationTypeAndApplicationStatus(String applicationType,
			String applicationStatus);

	List<Application> findByApplicationStatus(String status);

	boolean existsByApplicationNumber(Long applicationNumber);

	boolean existsByCustomerCustomerIdAndApplicationTypeApplicationType(Long customerId, String applicationType);
}
