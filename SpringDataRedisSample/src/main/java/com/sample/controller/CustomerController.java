package com.sample.controller;

import com.sample.dal.customer.Customer;
import com.sample.dal.Package.Package;
import com.sample.dal.Package.State;
import com.sample.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
          if(Long.toString((customer).getId())==Long.toString(Customer.getId()))
        	  return ResponseEntity.ok("The customer is already in the database,you can update him!!!");
//       	 return ResponseEntity.ok("The customer is already in the database,you can update him!!!");
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
    @RequestMapping("/values")
    public @ResponseBody Map<String, String> findAll() {
        Map<Object, Object> aa = redisRepository.findAllMovies();
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : aa.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, aa.get(key).toString());
        }
        return map;
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

