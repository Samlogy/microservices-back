package com.example.back.resource;

import com.example.back.model.Customer;
import com.example.back.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/customer")
@Slf4j
@Validated
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable Integer customerId){
        Customer response = customerService.getCustomerInfo(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}