package com.virtusa.banking.service;

import java.util.List;

import com.virtusa.banking.dto.AddBranchDto;
import com.virtusa.banking.dto.UpdateBranchDto;
import com.virtusa.banking.model.Branch;

public interface BranchService {
	Branch getBranchByIfsc(String ifsc);

	List<Branch> getBranchesByBankId(Long bankId);

	Branch deleteBranch(String ifsc);

	List<Branch> getAllBranches();

	void addBranch(AddBranchDto addBranchDto);

	boolean updateBranch(UpdateBranchDto updateBranchDto);
}
