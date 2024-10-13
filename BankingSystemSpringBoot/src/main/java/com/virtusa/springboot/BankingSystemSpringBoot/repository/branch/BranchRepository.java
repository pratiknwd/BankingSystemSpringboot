package com.virtusa.springboot.BankingSystemSpringBoot.repository.branch;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
	List<Branch> findByAddressAddressId(Long addressId);

	Boolean existsByIfsc(String ifsc);

	Optional<Branch> findByIfsc(String ifsc);
}
