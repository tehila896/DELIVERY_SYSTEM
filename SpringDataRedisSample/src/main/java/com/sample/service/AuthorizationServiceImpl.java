package com.sample.service;

import com.sample.dal.Customer;
import com.sample.dal.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    CustomerDao customerDao;


    public Boolean saveCustomer(Customer customer){
        return customerDao.saveCustomer(customer) ;
    }

    @Override
    public Customer findById(Long id) {

        return customerDao.findById(id);
    }
}
