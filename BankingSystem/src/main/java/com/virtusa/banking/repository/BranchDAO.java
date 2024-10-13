package com.virtusa.banking.repository;

import java.util.List;

import com.virtusa.banking.model.Branch;

public interface BranchDAO {
	
	void addBranch(Branch branch);
	
	Branch getBranchByIfsc(String ifsc);

	List<Branch> getBranchesByBankId(Long bankId);

	boolean updateBranch(Branch branch);

	Branch deleteBranch(String ifsc);

	List<Branch> getAllBranches();

	Branch deleteBranch(Branch branch);
}
