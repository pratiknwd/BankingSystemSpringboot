package com.virtusa.springboot.BankingSystemSpringBoot.repository.atm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.atm.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {
	List<Atm> findByAddressAddressId(Long addressId);
}
