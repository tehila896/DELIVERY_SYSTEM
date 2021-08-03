package com.sample.dal.deliveryMen;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.dal.Package.Package;
import com.sample.dal.Package.State;
import com.sample.dal.customer.Customer;

public interface DeliveryMenDao {

	public Boolean saveDeliveryMen(DeliveryMenDao deliveryMenDao) ;
    public Customer findDeliveryMenById(Long id) ;
    
}
