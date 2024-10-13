package com.virtusa.springboot.BankingSystemSpringBoot.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.card.DebitCardTypeDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.card.CardTypeMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCardType;
import com.virtusa.springboot.BankingSystemSpringBoot.service.card.DebitCardTypeService;

@RestController
@RequestMapping("/admin/cardtype")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class CardTypeController {

    @Autowired
    private DebitCardTypeService debitCardTypeService;

    @Autowired
    private CardTypeMapper cardTypeMapper;

    @GetMapping("/debitcardtypes")
    public ResponseEntity<List<DebitCardType>> getAllDebitCradTypes() {
        return ResponseEntity.ok(debitCardTypeService.getAllDebitCardTypes());
    }

    @PostMapping("/addDebitCardType")
    public ResponseEntity<ResponseDto> addDebitCardType(@RequestBody DebitCardTypeDto debitCardTypeDto) {

        DebitCardType debitCardType = cardTypeMapper.toEntity(debitCardTypeDto);

        debitCardTypeService.addDebitCardType(debitCardType);

        ResponseDto response = new ResponseDto();
        response.setMessage("DebitCardType added successfully");

        return ResponseEntity.ok().body(response);

    }
}
