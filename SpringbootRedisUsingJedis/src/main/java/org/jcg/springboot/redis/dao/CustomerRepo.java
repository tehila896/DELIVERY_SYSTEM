package org.jcg.springboot.redis.dao;

import java.util.Map;
import org.jcg.springboot.redis.model.Customer;

public interface CustomerRepo {

	// Save a new customer.
	Boolean save(Customer customer);
	
	// Find customer by id.
	Customer findById(String id);
	
	// Final all customers.
	Map<String, Customer> findAll();
	
	// Delete a customer.
	Boolean delete(String id);
}
