package org.jcg.springboot.redis.service;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.dao.DeliveryMenRepo;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeliveryMenService implements DeliveryMenRepo{
	private final String DELIVERYMEN_CACHE = "DELIVERYMEN";
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, DeliveryMen> hashOperations;

	@Autowired
	PackageService servicePackage;
	
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public Boolean save(DeliveryMen deliverMen) {
		try {
			hashOperations.put(DELIVERYMEN_CACHE, deliverMen.getId(), deliverMen);
			return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }	
	}

	@Override
	public DeliveryMen findById(String id) {
		return (DeliveryMen) hashOperations.get(DELIVERYMEN_CACHE, id);
	}

	@Override
	public Map<String, DeliveryMen> findAll() {
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
		Map<String,DeliveryMen> map_DeliveryMens=findAll();
		Map<String,Double> map_temp=new HashMap<>();
		for (DeliveryMen item : map_DeliveryMens.values()) {
			if(item.isStatosCurrentlyWorking()==false)
			   map_temp.put(item.getId(),Point2D.distance(deliveryPackage.getP_source().x,deliveryPackage.getP_source().y,item.getPosition().x,item.getPosition().y));
		}
		String min_key = Collections.min(map_temp.entrySet(), Map.Entry.comparingByValue()).getKey();
		DeliveryMen deliveryMen=findById(min_key);
		deliveryMen.setStatosCurrentlyWorking(true);
		save(deliveryMen);
		
		deliveryPackage.setDelivery_id(min_key);
		servicePackage.save(deliveryPackage);
		}
}
