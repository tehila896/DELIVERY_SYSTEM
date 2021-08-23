package DelvierySystem.kafka;

/*
* KafkaReciver listens to Kafka,
And when a customer orders a new package it pulls out the new package id from it*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import DelvierySystem.model.Package;
import DelvierySystem.service.DeliveryPersonService;
import DelvierySystem.service.PackageService;

@Component
public class KafkaReciver {
	
	@Autowired
	DeliveryPersonService deliveryMenService;
	
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
