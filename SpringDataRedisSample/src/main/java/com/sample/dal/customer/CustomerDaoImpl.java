package com.sample.dal.customer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "customer";

    public Boolean saveCustomer(Customer customer) {
        try {
            Map customerHash  = new ObjectMapper().convertValue(customer, Map.class);
            redisTemplate.opsForHash().put(KEY, Long.toString(customer.getId()), customerHash);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer findCustomerById(Long id) {

        Map customerMap = (Map) redisTemplate.opsForHash().get(KEY, id);
        Customer customer = new ObjectMapper().convertValue(customerMap, Customer.class);
        return customer;
    }
}
