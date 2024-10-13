package com.virtusa.springboot.BankingSystemSpringBoot.repository.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByUserUserId(Long userId);

	List<Customer> findByAddressAddressId(Long addressId);

	boolean existsByUserUserId(Long userId);

	boolean existsByUserUsername(String username);

	Optional<Customer> findByUserUsername(String username);
}
