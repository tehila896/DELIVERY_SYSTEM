package com.sample.service;

import com.sample.dal.Customer;

public interface AuthorizationService {


    public Boolean saveCustomer(Customer customer);

    public Customer findById(Long id);


}
