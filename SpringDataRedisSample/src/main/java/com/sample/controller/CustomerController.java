package com.sample.controller;

import com.sample.dal.customer.Customer;
import com.sample.dal.Package.Package;
import com.sample.dal.Package.State;
import com.sample.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
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
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer Customer) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(Customer, Customer.class);
        if(authorizationService.findCustomerById(customer.getId())!=null)
       	 return ResponseEntity.ok("The customer is already in the database,you can update him!!!");
        Boolean result =authorizationService.saveCustomer(customer);
        if (result) {
            return ResponseEntity.ok("A new customer is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }
    @RequestMapping(value = "/updadateCustomer", method = RequestMethod.PUT)
    public ResponseEntity<String> updadateCustomer(@Valid @RequestBody Customer Customer) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(Customer, Customer.class);
        Boolean result =authorizationService.saveCustomer(customer);
        if (result) {
            return ResponseEntity.ok("customer is updated!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }
    @RequestMapping(value = "/orderPackage", method = RequestMethod.POST)
    public ResponseEntity<String> orderPackage(@Valid @RequestBody Package Package) {
        ModelMapper modelMapper = new ModelMapper();
        Package package_ = modelMapper.map(Package, Package.class);
        Boolean result =authorizationService.savePackage(package_);
        if (result) {
            return ResponseEntity.ok("A new package is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }
    }
    @RequestMapping(value = "/checkState", method = RequestMethod.POST)
    public ResponseEntity<State> checkState(@Valid @RequestBody Long id) {     
        State result = authorizationService.findStatePackage(id);
        return ResponseEntity.ok(result);

    }
    @RequestMapping(value = "/findCustomer", method = RequestMethod.POST)
    public ResponseEntity<Customer> findCustomer(@RequestBody Customer Customer) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(Customer, Customer.class);       
        Customer result = authorizationService.findCustomerById(customer.getId());
        return ResponseEntity.ok(result);
    }
}

