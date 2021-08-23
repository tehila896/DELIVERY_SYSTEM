package DelvierySystem.service;

/*DistributedLock She realizes the idea
Locking and releasing an object
In our case it locks and releases the DeliveryPerson*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DelvierySystem.model.DeliveryPerson;

@Component
public class DistributedLock {

	@Autowired
	DeliveryPersonService deliveryMenService;

	  public boolean getLock (String lockId) {
		  DeliveryPerson temp=deliveryMenService.findById(lockId);
		  temp.setLock("lock");
		  Boolean success = deliveryMenService.save(temp);
	      return success;
	  }
	  public boolean releaseLock (String lockId) {
		  DeliveryPerson temp=deliveryMenService.findById(lockId);
		  temp.setLock("unlock");
		  Boolean success = deliveryMenService.save(temp);
	      return success;
	  }
}
