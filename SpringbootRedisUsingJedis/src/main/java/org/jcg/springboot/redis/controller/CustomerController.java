package org.jcg.springboot.redis.controller;

import java.awt.Point;
import java.util.Map;
import javax.validation.Valid;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.State;
import org.jcg.springboot.redis.kafka.KafkaSender;
import org.jcg.springboot.redis.model.Customer;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.service.CustomerService;
import org.jcg.springboot.redis.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/redis/customer")
public class CustomerController {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService serviceCustomer;

	@Autowired
	PackageService servicePackage;

	@Autowired
	KafkaSender kafkaSender;

	// Save a new customer.
	// Url - http://localhost:10091/api/redis/add_customer
	@PostMapping("/add_customer")
	public ResponseEntity<String> save(@Valid @RequestBody final Customer customer) {
        try {
        	Customer temp=serviceCustomer.findById(customer.getId());
        	if(temp.getId()==null)
             	  return null;
        }
        catch(Exception ex)
        {
        	    Boolean result =serviceCustomer.save(customer);;
      	        if (result) {
      	        	LOG.info("Saving the new customer to the redis.");
      	            return ResponseEntity.ok("A new customer is saved!!!");
      	        } 
      	        else 
      	            return ResponseEntity.ok("An error occured!!!");
        }
		return ResponseEntity.ok("The customer id is already in the database,you can update him!!!"); 	
	}
	
	// Update a new customer.
	// Url - http://localhost:10091/api/redis/update_customer
	@PutMapping("/update_customer")
	public ResponseEntity<String> update(@Valid @RequestBody final Customer customer) {
	      Boolean result =serviceCustomer.save(customer);;
	      if (result) 
	    	  return ResponseEntity.ok("customer is updated!!!");
	      else 
	           return ResponseEntity.ok("An error occured!!!");
       }
	// Order a new package.
	// Url - http://localhost:10091/api/redis/order_package
	@PostMapping("/order_package")
	public ResponseEntity<String> orderPackage(@Valid @RequestBody Package deliveryPackage) {
		try {				
			Customer temp=serviceCustomer.findById(deliveryPackage.getCustomer_id()); 
			if(temp.getId()==null)
           	  return null;
	        }
	        catch(Exception ex)
	        {
        	return ResponseEntity.ok("you have a mistake, The customer id is not in the database"); 	
	        }
		try {
			Package temp = servicePackage.findById(deliveryPackage.getId());
			if (temp.getId() == null)
				return null;
		} catch (Exception ex) {
			Boolean result = servicePackage.save(deliveryPackage);
			;
			if (result) {
				LOG.info("Saving the new Package to the redis.");
				kafkaSender.send(deliveryPackage.getId());
				return ResponseEntity.ok("A new Package is saved!!!");
			} else
				return ResponseEntity.ok("An error occured!!!");
		}
		return ResponseEntity.ok("This Package id is already in the database");

	}

	// Get package by id.
		// Url - http://localhost:10091/api/redis/customer/get/<package_id>
		@GetMapping("/get/PackageById/{package_id}")
		public ResponseEntity<Package> findPackageById(@PathVariable("package_id") final String package_id) {
			LOG.info("Fetching package with id= " + package_id);
			return ResponseEntity.ok(servicePackage.findById(package_id));
		}

	// check State
	// Url - http://localhost:10091/api/redis/customer/checkState/<package_id>
	@GetMapping("/getState/{package_id}")
	public ResponseEntity<State> checkState(@PathVariable("package_id") final String package_id) {
		LOG.info("Fetching state of package= " + package_id);
		return ResponseEntity.ok(servicePackage.findById(package_id).getList_states().getLast());
	}
	
	// Get customer by id.
	// Url - http://localhost:10091/api/redis/customer/get/<customer_id>
	@GetMapping("/get/{id}")
	public Customer findById(@PathVariable("id") final String id) {
		LOG.info("Fetching customer with id= " + id);
		return serviceCustomer.findById(id);
	}

	// Delete customer by id.
	// Url - http://localhost:10091/api/redis/customer/delete_customer/<customer_id>
	@DeleteMapping("/delete_customer/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") final String id) {
		try {
			Customer customer=serviceCustomer.findById(id);
			serviceCustomer.delete(customer.getId());
			LOG.info("Deleting customer with id= " + id);
			return ResponseEntity.ok("customer is deleted!!!");
		}
		catch (Exception e) {
			return ResponseEntity.ok("id customer not found!!!");
		}
	}
}
