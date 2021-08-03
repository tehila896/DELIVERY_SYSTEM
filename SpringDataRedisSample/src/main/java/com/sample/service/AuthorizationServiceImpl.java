package com.sample.service;
import com.sample.dal.Package.Package;
import com.sample.dal.Package.PackageDao;
import com.sample.dal.Package.State;
import com.sample.dal.customer.Customer;
import com.sample.dal.customer.CustomerDao;
import com.sample.dal.deliveryMen.DeliveryMen;
import com.sample.dal.deliveryMen.DeliveryMenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    PackageDao packageDao;
    
    @Autowired
    DeliveryMenDao deliveryMenDao;
    
    public Boolean saveCustomer(Customer customer){
       
    	return customerDao.saveCustomer(customer) ;
    }

    @Override
    public Customer findCustomerById(Long id) {

        return customerDao.findCustomerById(id);
    }

	@Override
	public Boolean savePackage(Package package_) {
		return packageDao.savePackage(package_) ;
	}

	@Override
	public Package findPackageById(Long id) {
		  return packageDao.findPackageById(id);
	}
	@Override
	public State findStatePackage(Long id) {
		return packageDao.findStatePackage((id));
	}

	@Override
	public Boolean saveDeliveryMen(DeliveryMen deliveryMen) {
		return deliveryMenDao.saveDeliveryMen(deliveryMen) ;
	}

	@Override
	public DeliveryMen findDeliveryMenById(Long id) {
		  return deliveryMenDao.findDeliveryMenById(id);
	}

}
