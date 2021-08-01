package com.sample.dal;

public interface CustomerDao {

    public Boolean saveCustomer(Customer customer) ;
    public Customer findById(Long id) ;


}
