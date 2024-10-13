package com.virtusa.springboot.BankingSystemSpringBoot.repository.mobileApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.mobileApp.MobileApp;
@Repository
public interface MobileAppRepository extends JpaRepository<MobileApp, Long>{
	List<MobileApp> findByCustomerCustomerId(Long customerId);
}
