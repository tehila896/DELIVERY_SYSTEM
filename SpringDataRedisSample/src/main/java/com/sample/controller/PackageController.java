package com.sample.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dal.customer.Customer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RestController
@RequestMapping("/api")
public class PackageController {
//	 @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
//	    public ResponseEntity<String> addPackage(@Valid @RequestBody Customer Customer) {
//	        ModelMapper modelMapper = new ModelMapper();
//	        Customer customer = modelMapper.map(Customer, Customer.class);
//	        Boolean result =authorizationService.savePackage(customer);
//	        if (result) {
//	            return ResponseEntity.ok("A new customer is saved!!!");
//	        } else {
//	            return ResponseEntity.ok("An error occured!!!");
//	        }
//
//	    }
}
