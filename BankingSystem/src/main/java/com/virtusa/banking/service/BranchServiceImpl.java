package com.virtusa.banking.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.dto.AddBranchDto;
import com.virtusa.banking.dto.UpdateBranchDto;
import com.virtusa.banking.exception.CustomDatabaseException;
import com.virtusa.banking.exception.ResourceNotFoundException;
import com.virtusa.banking.model.Bank;
import com.virtusa.banking.model.Branch;
import com.virtusa.banking.repository.BranchDAO;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDAO branchDAO;

	@Autowired
	private BankService bankService;

	@Override
	@Transactional
	public void addBranch(AddBranchDto addBranchDto) {
		Bank bank = bankService.findBankByName(addBranchDto.getBankName());
		if (bank == null) {
			throw new ResourceNotFoundException("Bank", "bankName", addBranchDto.getBankName());
		}
		Branch branch = new Branch();
		branch.setBank(bank);
		branch.setIfsc(addBranchDto.getIfsc());
		branch.setAddress(addBranchDto.getAddress());
		try {
			bank.getBranches().add(branch);
			bankService.saveBank(bank);
		} catch (PersistenceException e) {
			throw new CustomDatabaseException("Database Transaction failed");
		}
	}

	@Override
	public Branch getBranchByIfsc(String ifsc) {
		return branchDAO.getBranchByIfsc(ifsc);
	}

	@Override
	public List<Branch> getBranchesByBankId(Long bankId) {
		return branchDAO.getBranchesByBankId(bankId);
	}

	@Override
	@Transactional
	public boolean updateBranch(UpdateBranchDto updateBranchDto) {
		Branch branch = branchDAO.getBranchByIfsc(updateBranchDto.getIfsc());
		branch.setAddress(updateBranchDto.getAddress());
		return branchDAO.updateBranch(branch);
	}

	@Override
	@Transactional
	public Branch deleteBranch(String ifsc) {

		Bank bank = branchDAO.getBranchByIfsc(ifsc).getBank();
		Branch branch = bank.getBranches().stream().filter(b -> b.getIfsc().equalsIgnoreCase(ifsc)).findFirst().get();
		bank.getBranches().remove(branch);
		bankService.saveBank(bank);
		branchDAO.deleteBranch(branch);
		return branch;
	}

	@Override
	public List<Branch> getAllBranches() {
		return branchDAO.getAllBranches();
	}

}
