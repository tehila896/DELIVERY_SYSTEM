package org.jcg.springboot.redis.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class Package implements Serializable{
static final long serialVersionUID = 1L;
@JsonProperty
@Valid
@Id	
String id;
@JsonProperty
@Valid
@NotNull(message = "customer_id can`t be null")
String customer_id;
@JsonProperty
@Valid
String delivery_id;
@JsonProperty
@Valid
Point p_source;
@JsonProperty
@Valid
Point p_destninon;
@JsonProperty
@Valid
Date date_orde;
@JsonProperty
@Valid
LinkedList<State> list_states=new LinkedList<State>();
@JsonProperty
@Valid
Boolean statosCompletedPackage;
@JsonProperty
@Valid
PayMentEvent payment;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public void setCustomer_id(String customer_id) {
	this.customer_id = customer_id;
}
public void setDelivery_id(String delivery_id) {
	this.delivery_id = delivery_id;
}
public String getCustomer_id() {
	return customer_id;
}
public void setDate_orde(Date date_orde) {
	this.date_orde = date_orde;
}
public Point getP_source() {
	return p_source;
}
public void setP_source(Point p_source) {
	this.p_source = p_source;
}
public Point getP_destninon() {
	return p_destninon;
}
public void setP_destninon(Point p_destninon) {
	this.p_destninon = p_destninon;
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
public PayMentEvent getPayment() {
	return payment;
}
public void setPayment(PayMentEvent payment) {
	this.payment = payment;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public Date getDate_orde() {
	return date_orde;
}
@Override
public String toString() {
	return "Package [id=" + id + ", p_source=" + p_source + ", p_destninon=" + p_destninon + ", date_orde=" + date_orde
			+ ", list_states=" + list_states + ", statosCompletedPackage=" + statosCompletedPackage + ", payment="
			+ payment + "]";
}

}
