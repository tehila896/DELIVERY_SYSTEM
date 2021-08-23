package DelvierySystem.service;

/*DeliveryPersonService implements the required actions belonging to the DeliveryPersonRepo interface*/

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import DelvierySystem.controller.DeliveryMenController;
import DelvierySystem.dao.DeliveryPersonRepo;
import DelvierySystem.model.DeliveryPerson;
import DelvierySystem.model.Package;
import DelvierySystem.model.PayMentEvent;
import DelvierySystem.model.PriceField;
import DelvierySystem.model.UnitOfDistance;
@Service
public class DeliveryPersonService implements DeliveryPersonRepo{
	
	private final String DELIVERYMEN_CACHE = "DELIVEARYMENSS";
	
	private static final Logger LOG = LoggerFactory.getLogger(DeliveryMenController.class);

	@Autowired
	PriceFieldService servicePriceFieldService;
	 @Autowired
	 DistributedLock distributedLock;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, DeliveryPerson> hashOperations;

	@Autowired
	PackageService servicePackage;
	
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public Boolean save(DeliveryPerson deliverMen) {
		try {
			hashOperations.put(DELIVERYMEN_CACHE, deliverMen.getId(), deliverMen);
			return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }	
	}

	@Override
	public DeliveryPerson findById(String id) {
		return (DeliveryPerson) hashOperations.get(DELIVERYMEN_CACHE, id);
	}

	@Override
	public Map<String, DeliveryPerson> findAll() {
		return hashOperations.entries(DELIVERYMEN_CACHE);
	}

	@Override
	public Boolean delete(String id) {
		try 
		{
		hashOperations.delete(DELIVERYMEN_CACHE, id);
		return true;
        } 
		catch (Exception e) {
            e.printStackTrace();
            return false;
        }	
	}
	@Override
	public void calclute_Distance(Package deliveryPackage) {
		Double distancePackage=distance_Between_LatLong(deliveryPackage.getP_source().getX(),deliveryPackage.getP_source().getY(),deliveryPackage.getP_destninon().getX(),deliveryPackage.getP_destninon().getY());
		Map<String,DeliveryPerson> map_DeliveryMens=findAll();
		Map<String,Double> map_temp=new HashMap<>();
		UnitOfDistance unitOfDistance;
		for (DeliveryPerson item : map_DeliveryMens.values())
		{
			if(!(item.isStatosCurrentlyWorking()) &&(item.getLock().equals("unlock")) && (item.getCarryingCapacity().getValue() >= deliveryPackage.getCarryingCapacity().getValue()))
			 {
			Double distanceWay=(distance_Between_LatLong(deliveryPackage.getP_source().getX(),deliveryPackage.getP_source().getY(),item.getPosition().getX(),item.getPosition().getY())+distancePackage);
			if(distanceWay<=5)
				unitOfDistance=UnitOfDistance.FIVE_KM;
			else
				if(distanceWay<=15)
					unitOfDistance=UnitOfDistance.FIFTEEN_KM;
				else
					unitOfDistance=UnitOfDistance.FIFTY_KM;
			if(item.getUnitOfDistance().getValue() >= unitOfDistance.getValue())
			{
			Double sumHours=distanceWay/item.getSpeed();
			map_temp.put(item.getId(),sumHours*item.getPrice_carryingCapacity());
			}
			}
		}
		String min_key = Collections.min(map_temp.entrySet(), Map.Entry.comparingByValue()).getKey();
		deliveryPackage.setPayment(new PayMentEvent("visa", Collections.min(map_temp.entrySet(), Map.Entry.comparingByValue()).getValue()));
		DeliveryPerson deliveryMen=findById(min_key);
		Double distanceWay=(distance_Between_LatLong(deliveryPackage.getP_source().getX(),deliveryPackage.getP_source().getY(),deliveryMen.getPosition().getX(),deliveryMen.getPosition().getY())+distancePackage);
		if(distanceWay<=5)
			deliveryPackage.setUnitOfDistance(UnitOfDistance.FIVE_KM);
		else
			if(distanceWay<=15)
				deliveryPackage.setUnitOfDistance(UnitOfDistance.FIFTEEN_KM);
			else
				deliveryPackage.setUnitOfDistance(UnitOfDistance.FIFTY_KM);
		deliveryMen.setStatosCurrentlyWorking(true);
		save(deliveryMen);	
		deliveryPackage.setDelivery_id(min_key);
		deliveryPackage.setDateStartOrder(new Date());
		servicePackage.save(deliveryPackage);
		PriceField row=servicePriceFieldService.findById(deliveryPackage.getUnitOfDistance());
        double column=row.getColumns().get(deliveryPackage.getCarryingCapacity());
        //locked the DeliveryPerson when it is not profitable for the company
		if(deliveryPackage.getPayment().getValue()>column)
	    {
          boolean lock = distributedLock.getLock (deliveryMen.getId());	
          LOG.info("deliveryMen "+deliveryMen.getId()+" is locked "+ lock);
		}
        }

	@Override	
	public double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }
	
}
