package com.sample.dal.Package;

import java.awt.Point;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
@Data
@RedisHash("Package")
public class Package {
@Id
Long id;
Long customer_id;
Long deliveryMen_id;
Point p_source;
Point p_destninon;
LocalDate date_orde;
LinkedList<State> list_states=new LinkedList<State>();
Boolean statosCompletedPackage;
public Boolean getStatosCompletedPackagee() {
	return statosCompletedPackage;
}
public void setStatosCompletedPackage_(Boolean statosCompletedPackage) {
	this.statosCompletedPackage = statosCompletedPackage;
}
paymentEvent payment=new paymentEvent();

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public LinkedList<State> getList_states() {
	return list_states;
}
public void setList_states(LinkedList<State> list_states) {
	this.list_states = list_states;
}
public Boolean getStatosCompletedPackage() {
	return statosCompletedPackage;
}
public void setStatosCompletedPackage(Boolean statosCompletedPackage) {
	this.statosCompletedPackage = statosCompletedPackage;
}
public Long getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(Long customer_id) {
	this.customer_id = customer_id;
}
public Long getDeliveryMen_id() {
	return deliveryMen_id;
}
public void setDeliveryMen_id(Long deliveryMen_id) {
	this.deliveryMen_id = deliveryMen_id;
}}
