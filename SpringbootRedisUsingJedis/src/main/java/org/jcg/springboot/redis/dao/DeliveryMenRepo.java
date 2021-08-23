package org.jcg.springboot.redis.dao;

import org.jcg.springboot.redis.model.Package;
import java.util.Map;
import org.jcg.springboot.redis.model.DeliveryMen;

public interface DeliveryMenRepo {
	
	// Save a new deliveryMen.
	Boolean save(DeliveryMen deliveryMen);
	
	// Find deliveryMen by id.
	DeliveryMen findById(String id);
	
	// Final all deliveryMens.
	Map<String, DeliveryMen> findAll();
	
	// Delete a deliveryMen.
	Boolean delete(String id);
	
	// find the closest deliveryMend.
	void calclute_Distance(Package deliveryPackage);
	
	double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2);
}
