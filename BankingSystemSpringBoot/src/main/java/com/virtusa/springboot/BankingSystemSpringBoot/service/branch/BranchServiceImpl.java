package com.virtusa.springboot.BankingSystemSpringBoot.service.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.branch.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch addBranch(Branch branch) {
        if (branchRepository.existsByIfsc(branch.getIfsc())) {
            throw new ResourceAlreadyExistsException("Branch", "Ifsc", branch.getIfsc());
        }
        System.out.println("----------------------------------");
        System.out.println(branch.getAddress());
        return branchRepository.save(branch);
    }

    @Override
    public Branch getBranchById(Long branchId) {

        return branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "branchId", branchId + ""));
    }

    @Override
    public Branch updateBranch(Long branchId, Branch newBranch) {
        Branch oldBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "branchId", branchId + ""));

        if (oldBranch.getIfsc().equals(newBranch.getIfsc())) {
            // oldBranch.setIfsc(newBranch.getIfsc());
            Long addressId = oldBranch.getAddress().getAddressId();
            oldBranch.setAddress(newBranch.getAddress());
            oldBranch.getAddress().setAddressId(addressId);
        } else if (branchRepository.existsByIfsc(newBranch.getIfsc())) {
            throw new ResourceAlreadyExistsException("Branch", "Ifsc", newBranch.getIfsc());
        }

        return branchRepository.save(oldBranch);
    }

    @Override
    public void deleteBranch(Long branchId) {
        Branch branch = getBranchById(branchId);
        branchRepository.delete(branch);
    }

    @Override
    public Branch getBranchByIfsc(String ifsc) {
        return branchRepository.findByIfsc(ifsc)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "ifsc", ifsc));
    }

    @Override
    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

}
