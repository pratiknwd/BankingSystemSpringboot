package com.virtusa.springboot.BankingSystemSpringBoot.service.branch;

import java.util.List;

import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;

public interface BranchService {
    Branch addBranch(Branch branch);

    Branch getBranchById(Long branchId);

    List<Branch> getAllBranch();

    Branch updateBranch(Long branchId, Branch entity);

    void deleteBranch(Long branchId);

    Branch getBranchByIfsc(String ifsc);

}
