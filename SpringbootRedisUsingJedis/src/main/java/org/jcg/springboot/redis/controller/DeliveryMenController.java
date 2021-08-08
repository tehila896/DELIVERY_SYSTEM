package org.jcg.springboot.redis.controller;

import java.awt.Point;
import java.util.LinkedList;
import javax.validation.Valid;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.State;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/redis/deliveryMen")
public class DeliveryMenController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DeliveryMenController.class);

	@Autowired
	DeliveryMenService serviceDeliveryMen;
	
	@Autowired
	PackageService servicePackage;
	// Save a new deliveryMen.
	// Url - http://localhost:10091/api/redis/add_deliveryMen
	@PostMapping("/add_deliveryMen")
	public ResponseEntity<String> save(@Valid @RequestBody final DeliveryMen deliveryMen) {
        try {
        	DeliveryMen temp=serviceDeliveryMen.findById(deliveryMen.getId());
        	if(temp.getId()==null)
             	  return null;
        }
        catch(Exception ex)
        {
        	    Boolean result =serviceDeliveryMen.save(deliveryMen);;
      	        if (result) {
      	        	LOG.info("Saving the new deliveryMen to the redis.");
      	            return ResponseEntity.ok("A new deliveryMen is saved!!!");
      	        } 
      	        else 
      	            return ResponseEntity.ok("An error occured!!!");
        }
		return ResponseEntity.ok("The deliveryMen is already in the database,you can update him!!!"); 	
	}
	
	// Update a new deliveryMen.
	// Url - http://localhost:10091/api/redis/update_deliveryMen
	@PutMapping("/update_deliveryMen")
	public ResponseEntity<String> update(@Valid @RequestBody final DeliveryMen deliveryMen) {
	Boolean result =serviceDeliveryMen.save(deliveryMen);;
	if (result) 
		return ResponseEntity.ok("deliveryMen is updated!!!");
	else 
		return ResponseEntity.ok("An error occured!!!");
    }
	
	// Update deliveryMen position.
	// Url - http://localhost:10091/api/redis/updadate_Delverymen_Position
	@PutMapping("/updadate_Delverymen_Position")
	public ResponseEntity<String> updadate_Delverymen_Position(@Valid @RequestBody String id,@Valid @RequestBody Point position) {
    DeliveryMen deliveryMen_ = serviceDeliveryMen.findById(id);
    deliveryMen_.setPosition(position);
    Boolean result =serviceDeliveryMen.save(deliveryMen_);
    if (result) 
        return ResponseEntity.ok("postion is updated!!!");
    else 
        return ResponseEntity.ok("An error occured!!!");
    }
	
	// Update deliveryMen statos.
	// Url - http://localhost:10091/api/redis/update_Statos_Package
	@PutMapping("/update_Statos_Package")
	public ResponseEntity<String> update_Statos_Package(@Valid @RequestBody String id,@Valid @RequestBody Boolean statos) {
	Package package_ = servicePackage.findById(id);
	package_.setStatosCompletedPackage(statos);
	Boolean result =servicePackage.save(package_);
	if (result) 
	        return ResponseEntity.ok("statos is updated!!!");
	else 
	        return ResponseEntity.ok("An error occured!!!");
	}
	
	// Update deliveryMen state.
	// Url - http://localhost:10091/api/redis/update_State
	@PutMapping("/update_State_Package")
	public ResponseEntity<String> update_State_Package(@Valid @RequestBody String id,@Valid @RequestBody State state) {
	Package package_ = servicePackage.findById(id);
	LinkedList<State> list_states=package_.getList_states();
	list_states.add(state);
	package_.setList_states(list_states);
	Boolean result =servicePackage.save(package_);
	if (result) 
		return ResponseEntity.ok("state is updated!!!");
	else 
		return ResponseEntity.ok("An error occured!!!");
		}	
	// Update deliveryMen state.
	// Url - http://localhost:10091/api/redis/update_DeliveryMen_statosCurrentlyWorking
	@PutMapping("/update_DeliveryMen_statosCurrentlyWorking")
	public ResponseEntity<String> update_DeliveryMen_statosCurrentlyWorking(@Valid @RequestBody String id,@Valid @RequestBody Boolean statosCurrentlyWorking) {
    DeliveryMen deliveryMen_ = serviceDeliveryMen.findById(id);
    deliveryMen_.setStatosCurrentlyWorking(statosCurrentlyWorking);
    Boolean result =serviceDeliveryMen.save(deliveryMen_);
    if (result) 
    	return ResponseEntity.ok("statosCurrentlyWorking is updated!!!");
    else 
    	return ResponseEntity.ok("An error occured!!!");
			}	

}