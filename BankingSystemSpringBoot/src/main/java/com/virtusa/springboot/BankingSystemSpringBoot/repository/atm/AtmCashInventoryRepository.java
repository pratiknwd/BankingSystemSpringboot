package com.virtusa.springboot.BankingSystemSpringBoot.repository.atm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.atm.AtmCashInventory;
@Repository
public interface AtmCashInventoryRepository extends JpaRepository<AtmCashInventory, Long> {
	List<AtmCashInventory> findByAtmAtmId(Long atmId);
}
