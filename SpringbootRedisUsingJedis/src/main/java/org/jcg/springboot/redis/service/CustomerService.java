package org.jcg.springboot.redis.service;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.jcg.springboot.redis.dao.CustomerRepo;
import org.jcg.springboot.redis.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerRepo {

	private final String CUSTOMER_CACHE = "CUSTOMERS";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Customer> hashOperations;

	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}

	// Save operation.
	@Override
	public Boolean save(final Customer customer) {
		 try {
			 hashOperations.put(CUSTOMER_CACHE, customer.getId(), customer);
	         return true;

	        }
		 catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }		
	}

	// Find by customer id operation.
	@Override
	public Customer findById(final String id) {
		return (Customer) hashOperations.get(CUSTOMER_CACHE, id);
	}

	// Find all customer operation.
	@Override
	public Map<String, Customer> findAll() {
		return hashOperations.entries(CUSTOMER_CACHE);
	}

	// Delete customer by id operation.
	@Override
	public Boolean delete(String id) {
		try {
		hashOperations.delete(CUSTOMER_CACHE,id);
		return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }	
	}
}
