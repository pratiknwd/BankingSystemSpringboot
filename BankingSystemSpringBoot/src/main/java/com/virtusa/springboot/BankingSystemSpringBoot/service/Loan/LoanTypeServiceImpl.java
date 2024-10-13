package com.virtusa.springboot.BankingSystemSpringBoot.service.Loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.financialProducts.LoanType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.loan.LoanTypeRepository;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Override
    public List<LoanType> getAllLoanTypes() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanType addLoanType(LoanType loanTypeEntity) {
        if (loanTypeRepository.existsByLoanType(loanTypeEntity.getLoanType())) {
            throw new ResourceAlreadyExistsException("LoanType", "Loan Type", loanTypeEntity.getLoanType());
        }
        return loanTypeRepository.save(loanTypeEntity);
    }

    @Override
    public LoanType updateLoanType(Long loadTypeId, LoanType loanTypeEntity) {
        if (!loanTypeRepository.existsById(loadTypeId)) {
            throw new ResourceNotFoundException("LoanType", "Loan type id", loadTypeId + "");
        }

        // if (loanTypeRepository.existsByLoanType(loanTypeEntity.getLoanType())) {
        //     throw new ResourceAlreadyExistsException("LoanType", "Loan Type", loanTypeEntity.getLoanType());
        // }
        
        loanTypeEntity.setLoanTypeId(loadTypeId);
        return loanTypeRepository.save(loanTypeEntity);
    }

    @Override
    public void deleteLoanType(Long loadTypeId) {
        loanTypeRepository.deleteById(loadTypeId);
    }

}
