package org.jcg.springboot.redis.service;

import org.jcg.springboot.redis.model.DeliveryMen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributedLock {

	@Autowired
	DeliveryMenService deliveryMenService;

	  public boolean getLock (String lockId) {
		  DeliveryMen temp=deliveryMenService.findById(lockId);
		  temp.setLock("lock");
		  Boolean success = deliveryMenService.save(temp);
	      return success;
	  }
	  public boolean releaseLock (String lockId) {
		  DeliveryMen temp=deliveryMenService.findById(lockId);
		  temp.setLock("unlock");
		  Boolean success = deliveryMenService.save(temp);
	      return success;
	  }
}
