package com.sample.controller;

import com.sample.dal.Customer;
import com.sample.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public ResponseEntity<String> addCustomer(@RequestBody Customer Customer) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(Customer, Customer.class);
        Boolean result =authorizationService.saveCustomer(customer);
        if (result) {
            return ResponseEntity.ok("A new customer is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }

    @RequestMapping(value = "/findCustomer", method = RequestMethod.POST)
    public ResponseEntity<Customer> findCustomer(@RequestBody Customer Customer) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(Customer, Customer.class);       
        Customer result = authorizationService.findById(customer.getId());
        return ResponseEntity.ok(result);
    }
}

