package DelvierySystem.kafka;

/*When a customer orders a new package KafkaSender pushes the package id to kafka*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	String kafkaTopic = "packageId_topic";
	
	public void send(String package_id) {
	    kafkaTemplate.send(kafkaTopic, package_id);
	}
}
