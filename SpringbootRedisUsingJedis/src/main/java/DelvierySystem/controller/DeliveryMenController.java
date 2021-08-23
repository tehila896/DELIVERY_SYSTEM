package DelvierySystem.controller;

/*DeliveryMenController is responsible for actions performed by the deliveryPerson in the Delivery system*/

import java.util.LinkedList;
import javax.validation.Valid;

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

import DelvierySystem.model.DeliveryPerson;
import DelvierySystem.model.Package;
import DelvierySystem.model.PointD;
import DelvierySystem.model.State;
import DelvierySystem.service.DeliveryPersonService;
import DelvierySystem.service.DistributedLock;
import DelvierySystem.service.PackageService;
import DelvierySystem.service.PriceFieldService;

@RestController
@RequestMapping(value = "/api/redis/DeliveryPerson")
public class DeliveryMenController {

	private static final Logger LOG = LoggerFactory.getLogger(DeliveryPersonController.class);

	@Autowired
	DeliveryPersonService serviceDeliveryMen;

	@Autowired
	PackageService servicePackage;
	@Autowired
	PriceFieldService servicePriceFieldService;
	@Autowired
	DistributedLock distributedLock;

	// Save a new DeliveryPerson.
	// Url - http://localhost:10091/api/redis/add_deliveryMen
	@PostMapping("/add_DeliveryPerson")
	public ResponseEntity<String> save(@Valid @RequestBody final DeliveryPerson DeliveryPerson) {
		try {
			DeliveryPerson temp = serviceDeliveryMen.findById(DeliveryPerson.getId());
			if (temp.getId() == null)
				return null;
		} catch (Exception ex) {
			Boolean result = serviceDeliveryMen.save(DeliveryPerson);
			;
			if (result) {
				LOG.info("Saving the new DeliveryPerson to the redis");
				return ResponseEntity.ok("A new DeliveryPerson is saved!!!");
			} else
				return ResponseEntity.ok("An error occured!!!");
		}
		return ResponseEntity.ok("The DeliveryPerson id is already in the database,you can update him!!!");
	}

	// Update a new DeliveryPerson.
	// Url - http://localhost:10091/api/redis/update_DeliveryPerson
	@PutMapping("/update_DeliveryPerson")
	public ResponseEntity<String> update(@Valid @RequestBody final DeliveryPerson deliveryMen) {
		Boolean result = serviceDeliveryMen.save(deliveryMen);
		if (result)
		{
			LOG.info("Update the DeliveryPerson to the redis");
			return ResponseEntity.ok("DeliveryPerson is updated!!!");
		}
		else
			return ResponseEntity.ok("An error occured!!!");
	}

	// Update deliveryMen position.
	// Url -
	// http://localhost:10091/api/redis/updadate_DeliveryPerson_Position/position_Y/{position_Y}/position_X/{position_X}/id/{id}
	@PutMapping("/updadate_DeliveryPerson_Position/position_Y/{position_Y}/position_X/{position_X}/id/{id}")
	public ResponseEntity<String> updadate_DeliveryPerson_Position(@PathVariable("id") String id,
			@PathVariable("position_X") double position_X, @PathVariable("position_Y") double position_Y) {
		try {
			DeliveryPerson temp = serviceDeliveryMen.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setPosition(new PointD(position_X, position_Y));
			Boolean result = serviceDeliveryMen.save(temp);
			if (result)
			{
				LOG.info("Update the deliveryMen`postion to the redis");
				return ResponseEntity.ok("postion is updated!!!");
			}
			else
				return ResponseEntity.ok("An error occured!!!");

		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The deliveryMen id is not in the database");
		}
	}

	// Update deliveryMen statos.
	// Url - http://localhost:10091/api/redis/update_Statos_Package
	@PutMapping("/update_Statos_Package/statos/{statos}/id/{id}")
	public ResponseEntity<String> update_Statos_Package(@Valid @PathVariable("id") String id,
			@Valid @PathVariable("statos") Boolean statos) {
		try {
			Package temp = servicePackage.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setStatosCompletedPackage(statos);
			Boolean result = servicePackage.save(temp);
			if (result)
			{
				LOG.info("Update the Package`statos to the redis");
				return ResponseEntity.ok("statos is updated!!!");
			}
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
			{
				LOG.info("Update the DeliveryPerson`statos to the redis");
				return ResponseEntity.ok("state is updated!!!");
			}
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
			DeliveryPerson temp = serviceDeliveryMen.findById(id);
			if (temp.getId() == null)
				return null;
			temp.setStatosCurrentlyWorking(statosCurrentlyWorking);
			Boolean result = serviceDeliveryMen.save(temp);
			if (result)
			{
				LOG.info("statos Currently Working is updated!!!");
				return ResponseEntity.ok("statos Currently Working is updated!!!");
			}
			else
				return ResponseEntity.ok("An error occured!!!");

		} catch (Exception ex) {
			return ResponseEntity.ok("you have a mistake, The deliveryMen id is not in the database");
		}
	}
}