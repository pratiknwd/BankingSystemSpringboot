package com.virtusa.springboot.BankingSystemSpringBoot.repository.demographic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.demographic.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
