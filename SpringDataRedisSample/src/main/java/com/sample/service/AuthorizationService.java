package com.sample.service;

import com.sample.dal.customer.Customer;

public interface AuthorizationService {


    public Boolean saveCustomer(Customer customer);

    public Customer findCustomerById(Long id);
    
    public Boolean savePackage(Customer customer);

    public Customer findPackageById(Long id);


}
