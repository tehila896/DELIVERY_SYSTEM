package com.sample.service;

import com.sample.dal.customer.Customer;
import com.sample.dal.customer.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    CustomerDao customerDao;


    public Boolean saveCustomer(Customer customer){
        return customerDao.saveCustomer(customer) ;
    }

    @Override
    public Customer findCustomerById(Long id) {

        return customerDao.findCustomerById(id);
    }

	@Override
	public Boolean savePackage(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findPackageById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
