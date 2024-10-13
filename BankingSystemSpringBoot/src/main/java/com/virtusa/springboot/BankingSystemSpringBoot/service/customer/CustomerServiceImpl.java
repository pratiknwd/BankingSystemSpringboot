package com.virtusa.springboot.BankingSystemSpringBoot.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.springboot.BankingSystemSpringBoot.dto.customer.EditCustomerDetailsDto;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceAlreadyExistsException;
import com.virtusa.springboot.BankingSystemSpringBoot.exception.ResourceNotFoundException;
import com.virtusa.springboot.BankingSystemSpringBoot.mapper.AddressMapper;
import com.virtusa.springboot.BankingSystemSpringBoot.model.auth.User;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.repository.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Customer addCustomer(Customer customer, User user) {

        customer.setUser(user);

        if (customerRepository.existsByUserUserId(customer.getUser().getUserId())) {
            throw new ResourceAlreadyExistsException("Customer", "userId", user.getUserId() + "");
        }

        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(EditCustomerDetailsDto editCustomerDetailsDto, Customer customer) {
        customer.setName(editCustomerDetailsDto.getName());
        customer.getUser().setEmail(editCustomerDetailsDto.getEmail());
        long addressId = customer.getAddress().getAddressId();
        customer.setAddress(addressMapper.toEntity(editCustomerDetailsDto.getAddress()));
        customer.getAddress().setAddressId(addressId);
        customer.setGender(editCustomerDetailsDto.getGender());
        customer.setPhoneNumber(editCustomerDetailsDto.getPhoneNumber());

        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customer", customerId + ""));
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "username", username));
    }

    @Override
    public boolean isCustomerDetailPresent(String username) {
        return customerRepository.existsByUserUsername(username);
    }

}
