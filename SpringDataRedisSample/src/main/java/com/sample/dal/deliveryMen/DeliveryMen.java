package com.sample.dal.deliveryMen;

import java.awt.Point;
import org.springframework.data.redis.core.RedisHash;
import lombok.Data;

@Data
@RedisHash("DeliveryMen")
public class DeliveryMen {
Long id;
String contex_way;
String getSalary_way;
Point position;
boolean statosCurrentlyWorking;
public String getGetSalary_way() {
	return getSalary_way;
}
public void setGetSalary_way(String getSalary_way) {
	this.getSalary_way = getSalary_way;
}
public String getContex_way() {
	return contex_way;
}
public void setContex_way(String contex_way) {
	this.contex_way = contex_way;
}
public Long getId() {
	return id;
}
public Point getPosition() {
	return position;
}
public void setPosition(Point position) {
	this.position = position;
}
public void setId(Long id) {
	this.id = id;
}
public boolean isStatosCurrentlyWorking() {
	return statosCurrentlyWorking;
}
public void setStatosCurrentlyWorking(boolean statosCurrentlyWorking) {
	this.statosCurrentlyWorking = statosCurrentlyWorking;
}
}
