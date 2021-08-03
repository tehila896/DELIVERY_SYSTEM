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
paymentEvent payment=new paymentEvent();

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public List<State> getList_states() {
	return list_states;
}
public void setList_states(LinkedList<State> list_states) {
	this.list_states = list_states;
}}
