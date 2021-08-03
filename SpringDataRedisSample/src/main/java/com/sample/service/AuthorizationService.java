package com.sample.service;

import com.sample.dal.Package.Package;
import com.sample.dal.Package.State;
import com.sample.dal.customer.Customer;
import com.sample.dal.deliveryMen.DeliveryMen;

public interface AuthorizationService {


    public Boolean saveCustomer(Customer customer);

    public Customer findCustomerById(Long id);
    
    public Boolean savePackage(Package package_);

    public Package findPackageById(Long id);
 
    public State findStatePackage(Long id);

    public Boolean updateStatePackage(Long id,State state) ;

    public Boolean saveDeliveryMen(DeliveryMen deliveryMen);

    public DeliveryMen findDeliveryMenById(Long id);
}
