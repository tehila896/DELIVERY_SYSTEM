package org.jcg.springboot.redis.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderPackageId {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	String kafkaTopic = "packageId_topicssQ";
	
	public void sendPackageId(String package_id) {
	    kafkaTemplate.send(kafkaTopic, package_id);
	}

}
