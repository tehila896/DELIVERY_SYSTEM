package com.sample.dal.customer;

public interface CustomerDao {

    public Boolean saveCustomer(Customer customer) ;
    public Customer findCustomerById(Long id) ;

}
