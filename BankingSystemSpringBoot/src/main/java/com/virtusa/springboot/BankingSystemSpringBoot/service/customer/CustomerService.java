package com.virtusa.springboot.BankingSystemSpringBoot.service.customer;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.EditCustomerDetailsDto;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;

public interface CustomerService {

    public Customer addCustomer(Customer customer, User user);

    public void updateCustomer(EditCustomerDetailsDto editCustomerDetailsDto, Customer customer);

    public void updateCustomer(Customer customer);

    public Customer getCustomerById(Long customerId);

    public Customer getCustomerByUsername(String username);

    public boolean isCustomerDetailPresent(String username);
}
