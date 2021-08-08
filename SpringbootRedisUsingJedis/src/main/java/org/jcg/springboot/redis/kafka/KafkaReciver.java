package org.jcg.springboot.redis.kafka;

import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReciver {
	
	@Autowired
	DeliveryMenService deliveryMenService;
	
	@Autowired
	PackageService packageService;
	
	@KafkaListener(topics = "packageId_topic", groupId = "packages", concurrency = "4")
	public void listenGroupFoo(String package_id) {
		Package delveryPackage=packageService.findById(package_id);
		deliveryMenService.calclute_Distance(delveryPackage);
		System.out.println("Proccessing message with thread id: " + Thread.currentThread().getName());
	    System.out.println("Received Message in group packages: " + package_id);
	}
}
