package com.example.back.service;

import com.example.back.exception.NotFoundException;
import com.example.back.model.Customer;
import com.example.back.model.User;
import com.example.back.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerInfo(Integer userId) {
        return customerRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Customer does not exist with this ID: " + userId));
    }
}