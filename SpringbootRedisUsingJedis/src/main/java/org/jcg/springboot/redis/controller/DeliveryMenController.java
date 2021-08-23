package org.jcg.springboot.redis.controller;

import java.util.LinkedList;
import javax.validation.Valid;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.PointD;
import org.jcg.springboot.redis.model.State;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.DistributedLock;
import org.jcg.springboot.redis.service.PackageService;
import org.jcg.springboot.redis.service.PriceFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/redis/deliveryMen")
public class DeliveryMenController {

	private static final Logger LOG = LoggerFactory.getLogger(DeliveryMenController.class);

	@Autowired
	DeliveryMenService serviceDeliveryMen;

	@Autowired
	PackageService servicePackage;
	@Autowired
	PriceFieldService servicePriceFieldService;
	@Autowired
	DistributedLock distributedLock;

	// Save a new deliveryMen.
	// Url - http://localhost:10091/api/redis/add_deliveryMen
	@PostMapping("/add_deliveryMen")
	public ResponseEntity<String> save(@Valid @RequestBody final DeliveryMen deliveryMen) {
		try {
			DeliveryMen temp = serviceDeliveryMen.findById(deliveryMen.getId());
			if (temp.getId() == null)
				return null;
		} catch (Exception ex) {
			Boolean result = serviceDeliveryMen.save(deliveryMen);
			;
			if (result) {
				LOG.info("Saving the new deliveryMen to the redis");
				return ResponseEntity.ok("A new deliveryMen is saved!!!");
			} else
				return ResponseEntity.ok("An error occured!!!");
		}
		return ResponseEntity.ok("The deliveryMen id is already in the database,you can update him!!!");
	}

	// Update a new deliveryMen.
	// Url - http://localhost:10091/api/redis/update_deliveryMen
	@PutMapping("/update_deliveryMen")
	public ResponseEntity<String> update(@Valid @RequestBody final DeliveryMen deliveryMen) {
		Boolean result = serviceDeliveryMen.save(deliveryMen);
		;
		if (result)
			return ResponseEntity.ok("deliveryMen is updated!!!");
		else
			return ResponseEntity.ok("An error occured!!!");
	}

	// Update deliveryMen position.
	// Url -
	// http://localhost:10091/api/redis/updadate_Delverymen_Position/position_Y/{position_Y}/position_X/{position_X}/id/{id}
	@PutMapping("/updadate_Delverymen_Position/position_Y/{position_Y}/position_X/{position_X}/id/{id}")
	public ResponseEntity<String> updadate_Delverymen_Position(@PathVariable("id") String id,
			@PathVariable("position_X") double position_X, @PathVariable("position_Y") double position_Y) {
		try {
			DeliveryMen temp = serviceDeliveryMen.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setPosition(new PointD(position_X, position_Y));
			Boolean result = serviceDeliveryMen.save(temp);
			if (result)
				return ResponseEntity.ok("postion is updated!!!");
			else
				return ResponseEntity.ok("An error occured!!!");

		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The deliveryMen id is not in the database");
		}
	}

	// Update deliveryMen statos.
	// Url - http://localhost:10091/api/redis/update_Statos_Package
	@PutMapping("/updadate_Delverymen_Position/statos/{statos}/id/{id}")
	public ResponseEntity<String> update_Statos_Package(@Valid @PathVariable("id") String id,
			@Valid @PathVariable("statos") Boolean statos) {
		try {
			Package temp = servicePackage.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setStatosCompletedPackage(statos);
			Boolean result = servicePackage.save(temp);
			if (result)
				return ResponseEntity.ok("statos is updated!!!");
			else
				return ResponseEntity.ok("An error occured!!!");

		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The package id is not in the database");
		}
	}

	// Update deliveryMen state.
	// Url - http://localhost:10091/api/redis/update_State/state/{state}/id/{id}
	@PutMapping("/update_State_Package/id/{id}")
	public ResponseEntity<String> update_State_Package(@Valid @PathVariable("id") String id,
			@Valid @RequestBody State state) {
		try {
			Package temp = servicePackage.findById(id);
			LinkedList<State> list_states = temp.getList_states();
			list_states.add(state);
			temp.setList_states(list_states);
			Boolean result = servicePackage.save(temp);
			if (result)
				return ResponseEntity.ok("state is updated!!!");
			else
				return ResponseEntity.ok("An error occured!!!");
		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The package id is not in the database");
		}
	}

	// Update deliveryMen state.
	// Url -
	// http://localhost:10091/api/redis/update_DeliveryMen_statosCurrentlyWorking
	@PutMapping("/update_DeliveryMen_statosCurrentlyWorking/statosCurrentlyWorking/{statosCurrentlyWorking}/id/{id}")
	public ResponseEntity<String> update_DeliveryMen_statosCurrentlyWorking(@Valid @PathVariable("id") String id,
			@Valid @PathVariable("statosCurrentlyWorking") Boolean statosCurrentlyWorking) {
		try {
			DeliveryMen temp = serviceDeliveryMen.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setStatosCurrentlyWorking(statosCurrentlyWorking);
			Boolean result = serviceDeliveryMen.save(temp);
			if (result)
				return ResponseEntity.ok("statos Currently Working is updated!!!");
			else
				return ResponseEntity.ok("An error occured!!!");

		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The deliveryMen id is not in the database");
		}
	}
}