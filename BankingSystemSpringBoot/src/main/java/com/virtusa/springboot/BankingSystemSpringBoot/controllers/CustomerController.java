package com.virtusa.springboot.BankingSystemSpringBoot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.ResponseDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.AddBeneficiaryDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.BeneficiaryDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.CreateCustomerDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.CustomerDto;
import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.EditCustomerDetailsDto;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer.BeneficiaryMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.customer.CustomerMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Beneficiary;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.service.auth.AuthService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.BeneficiaryService;
import com.virtusa.springboot.BankingSystemSpringBoot.service.customer.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @PostMapping("/registerCustomer")
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = authService.getUserByUserName(username);

        Customer customer = customerService.addCustomer(customerMapper.toEntity(createCustomerDto), user);
        ResponseDto response = new ResponseDto();
        response.setMessage("Customer sucessfully registered with id : " + customer.getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomerWithId(@PathVariable("id") Long id) {

        CustomerDto details = customerMapper.toDto(customerService.getCustomerById(id));
        return ResponseEntity.status(HttpStatus.FOUND).body(details);
    }

    @PutMapping("/customer-update")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody EditCustomerDetailsDto editCustomerDetailsDto) {

        System.out.println(editCustomerDetailsDto);

        customerService.updateCustomer(editCustomerDetailsDto, getLoggedInCustomer());

        ResponseDto response = new ResponseDto();
        response.setMessage("Successfully updated");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/customerInfo")
    public ResponseEntity<CustomerDto> getCurrentCustomerInfo() {
        Customer customer = getLoggedInCustomer();
        CustomerDto customerDto = customerMapper.toDto(customer);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping("/addBeneficiary")
    public ResponseEntity<ResponseDto> addBeneficiary(@RequestBody AddBeneficiaryDto addBeneficiaryDto) {

        Customer customer = getLoggedInCustomer();

        addBeneficiaryDto.setCustomerId(customer.getCustomerId());

        Beneficiary beneficiary = beneficiaryMapper.toEntity(addBeneficiaryDto);

        beneficiaryService.addBeneficiary(beneficiary);
        ResponseDto response = new ResponseDto();
        response.setMessage("Beneficiary Created.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<BeneficiaryDto>> getBeneficiaries() {

        List<BeneficiaryDto> beneficiaries = beneficiaryService
                .getBeneficiaryByCustomerId(getLoggedInCustomer().getCustomerId()).stream()
                .map(b -> beneficiaryMapper.toDto(b)).collect(Collectors.toList());

        return ResponseEntity.ok().body(beneficiaries);
    }

    @GetMapping("/isCustomerDetailPresent")
    public ResponseEntity<ResponseDto> check() {
        System.out.println(SecurityContextHolder.getContext());
        System.out.println("**********************************************************");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isPresent = customerService.isCustomerDetailPresent(userName);
        ResponseDto response = new ResponseDto();
        if (isPresent) {
            response.setMessage("Present");
            return ResponseEntity.ok().body(response);
        }

        response.setMessage("Absent");
        return ResponseEntity.ok().body(response);
    }

    private Customer getLoggedInCustomer() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerService.getCustomerByUsername(userName);
    }

}
