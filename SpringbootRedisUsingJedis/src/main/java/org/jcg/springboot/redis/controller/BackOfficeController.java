package org.jcg.springboot.redis.controller;

import java.util.Map;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.Customer;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.service.CustomerService;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/redis/backOffice")
public class BackOfficeController {
	private static final Logger LOG = LoggerFactory.getLogger(BackOfficeController.class);

	@Autowired
	CustomerService serviceCustomer;

	@Autowired
	PackageService servicePackage;

	@Autowired
	DeliveryMenService serviceDeliveryMen;
	
	// Delete deliveryMen_ by id.
	// Url - http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>	
	 @DeleteMapping("/delete_deliveryMen/{id}")
	 public ResponseEntity<String> delete(@PathVariable("id") final String id) {
		try {
			DeliveryMen deliveryMen=serviceDeliveryMen.findById(id);
			serviceDeliveryMen.delete(deliveryMen.getId());
			LOG.info("Deleting deliveryMen with id= " + id);
			return ResponseEntity.ok("deliveryMen is deleted!!!");
		 }
		 catch (Exception e) {
			return ResponseEntity.ok("deliveryMen id not found!!!");
		 }
	   } 

		// Delete deliveryMen_ by id.
		// Url - http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>	
		 @DeleteMapping("/delete_deliveryMenpackage/{id}")
		 public ResponseEntity<String> delpac(@PathVariable("id") final String id) {
			try {
				Package deliveryMen=servicePackage.findById(id);
				servicePackage.delete(deliveryMen.getId());
				LOG.info("Deleting deliveryMen with id= " + id);
				return ResponseEntity.ok("deliveryMen is deleted!!!");
			 }
			 catch (Exception e) {
				return ResponseEntity.ok("deliveryMen id not found!!!");
			 }
		   } 
	    // Get all customers.
		// Url - http://localhost:10091/api/redis/backOffice/getall/customer
		@GetMapping("/getall/customer")
		public Map<String, Customer> findAll_customers() {
			LOG.info("Fetching all customers from the redis.");
			final Map<String, Customer> customerMap = serviceCustomer.findAll();
			// Todo - If developers like they can sort the map (optional).
			return customerMap;
		}
		 // Get all deliveryMens
		// Url - http://localhost:10091/api/redis/backOffice/getall/deliveryMen
		@GetMapping("/getall/deliveryMen")
		public Map<String, DeliveryMen> findAll_deliveryMens() {
			LOG.info("Fetching all DeliveryMens from the redis.");
			final Map<String, DeliveryMen> deliveryMenMap = serviceDeliveryMen.findAll();
			// Todo - If developers like they can sort the map (optional).
			return deliveryMenMap;
		}
		// Get all packages
		// Url - http://localhost:10091/api/redis/backOffice/getall/package
		@GetMapping("/getall/package")
		public Map<String, Package> findAll_packages() {
			LOG.info("Fetching all Packages from the redis.");
			final Map<String, Package> packageMenMap = servicePackage.findAll();
			// Todo - If developers like they can sort the map (optional).
			return packageMenMap;
		}
		// Get all packages group by customer id
		// Url - http://localhost:10091/api/redis/backOffice/getall/package_group_by_customer_id
		//@GetMapping("/getall/package_group_by_customer_id")
		//public Map<Object, java.util.List<Object>> findAll_packages_group_by_customer_id() {
		//	LOG.info("Fetching all DeliveryMen from the redis.");
		//	final Map<String, Package> packageMenMap = servicePackage.findAll();
		//	ArrayList<Package> list_package=new ArrayList<>();
		//	Map<Object, java.util.List<Object>> result = list_package.stream()
		//		    .collect(Collectors.groupingBy(map -> map.getId().toString(),
		//		                                   Collectors.mapping(map -> map,
		//		                                                      Collectors.toList())));
		//	return result;
		//		}
}
