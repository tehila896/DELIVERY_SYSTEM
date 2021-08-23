package org.jcg.springboot.redis.controller;

import java.util.Map;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.PriceField;
import org.jcg.springboot.redis.model.Customer;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.service.CustomerService;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.DistributedLock;
import org.jcg.springboot.redis.service.PackageService;
import org.jcg.springboot.redis.service.PriceFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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

	@Autowired
	PriceFieldService servicePriceFieldService;

	@Autowired
	DistributedLock distributedLock;

	// Save a new priceField.
	// Url - http://localhost:10091/api/redis/add_priceField
	@PostMapping("/add_priceField")
	public ResponseEntity<String> save_priceField(@Valid @RequestBody final PriceField priceField) {
		try {
			PriceField temp = servicePriceFieldService.findById(priceField.getRow());
			if (temp.getRow().toString() == null)
				return null;
		} catch (Exception ex) {
			Boolean result = servicePriceFieldService.save(priceField);
			;
			if (result) {
				LOG.info("Saving the new priceField to the redis.");
				return ResponseEntity.ok("A new priceField is saved!!!");
			} else
				return ResponseEntity.ok("An error occured!!!");
		}
		return ResponseEntity.ok("The priceField id is already in the database,you can update him!!!");
	}

	// Delete deliveryMen_ by id.
	// Url -
	// http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>
	@DeleteMapping("/delete_deliveryMen/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") final String id) {
		try {
			DeliveryMen deliveryMen = serviceDeliveryMen.findById(id);
			serviceDeliveryMen.delete(deliveryMen.getId());
			LOG.info("Deleting deliveryMen with id= " + id);
			return ResponseEntity.ok("deliveryMen is deleted!!!");
		} catch (Exception e) {
			return ResponseEntity.ok("deliveryMen id not found!!!");
		}
	}

	// Delete deliveryMen_ by id.
	// Url -
	// http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>
	@DeleteMapping("/delete_deliveryMenpackage/{id}")
	public ResponseEntity<String> delpac(@PathVariable("id") final String id) {
		try {
			Package deliveryMen = servicePackage.findById(id);
			servicePackage.delete(deliveryMen.getId());
			LOG.info("Deleting deliveryMen with id= " + id);
			return ResponseEntity.ok("deliveryMen is deleted!!!");
		} catch (Exception e) {
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

	// Get all PriceFields.
	// Url - http://localhost:10091/api/redis/backOffice/getall/PriceField
	@GetMapping("/getall/PriceField")
	public Map<String, PriceField> findAll_PriceField() {
		LOG.info("Fetching all PriceFields from the redis.");
		final Map<String, PriceField> customerMap = servicePriceFieldService.findAll();
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

	// Update deliveryMen state.
	// Url - http://localhost:10091/api/redis/realseLock/id/{id}
	@PutMapping("/realseLock/id/{id}")
	public ResponseEntity<String> realseLock(@Valid @PathVariable("id") String id) {
		try {
			DeliveryMen temp = serviceDeliveryMen.findById(id);
			distributedLock.releaseLock(id);
			return ResponseEntity.ok("delvieryMen " + temp.getFirstName() + " is unLocked");
		} catch (Exception ex) {
			return ResponseEntity.ok("An error occured!!!");
		}
	}
}
