package com.virtusa.springboot.BankingSystemSpringBoot.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.InvalidDataException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCardType;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.cards.DebitCardTypeRepository;

@Service
public class DebitCardTypeServiceImpl implements DebitCardTypeService {

    @Autowired
    private DebitCardTypeRepository debitCardTypeRepository;

    @Override
    public DebitCardType addDebitCardType(DebitCardType debitCardType) {
        if(debitCardType.getDailyLimit()>debitCardType.getWeeklyLimit()){
            throw new InvalidDataException("Daily limit must be less than weekly limit.");
        }
        if(debitCardType.getWeeklyLimit()>debitCardType.getMonthlyLimit()){
            throw new InvalidDataException("Weekly limit must be less than monthly limit.");
        }
        return debitCardTypeRepository.save(debitCardType);
    }

    @Override
    public void updateDebitCardType(DebitCardType debitCardType) {
        debitCardTypeRepository.save(debitCardType);
    }

    @Override
    public DebitCardType getDebitCardType(String debitCardType) {
        return debitCardTypeRepository.findByDebitCardType(debitCardType)
                .orElseThrow(() -> new ResourceNotFoundException("DebitCardType", "debitCardType", debitCardType + ""));
    }

    @Override
    public List<DebitCardType> getAllDebitCardTypes() {
        return debitCardTypeRepository.findAll();
    }

}
