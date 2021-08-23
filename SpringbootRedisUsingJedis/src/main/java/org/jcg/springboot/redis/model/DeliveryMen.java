package org.jcg.springboot.redis.model;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class DeliveryMen implements Serializable{
	static final long serialVersionUID = 1L;
	@JsonProperty
	@Valid
	@Id	
	String id;
	@Valid
	@JsonProperty
	@NotNull(message = "firstName can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "firstName must be a string")
	String firstName;
	@Valid
	@JsonProperty
	@NotNull(message = "lastName can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "lastName must be a string")
    String lastName;
	@Valid
	@JsonProperty
	boolean statosCurrentlyWorking;
	@Valid
	@JsonProperty
	@NotNull(message = "communication_way can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "communication_way must be a string")
	String communication_way;
	@Valid
	@JsonProperty
	@NotNull(message = "getSalary_way can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "getSalary_way must be a string")
	String getSalary_way;
	@Valid
	@JsonProperty
	PointD position;
	@Valid
	@JsonProperty
	double speed;
	@Valid
	@JsonProperty
	CarryingCapacity carryingCapacity;
	@Valid
	@JsonProperty
	UnitOfDistance unitOfDistance;
	@Valid
	@JsonProperty
	double price_carryingCapacity;
	String lock;
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public CarryingCapacity getCarryingCapacity() {
		return carryingCapacity;
	}
	public void setCarryingCapacity(CarryingCapacity carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}
	public UnitOfDistance getUnitOfDistance() {
		return unitOfDistance;
	}
	public void setUnitOfDistance(UnitOfDistance unitOfDistance) {
		this.unitOfDistance = unitOfDistance;
	}

	public double getPrice_carryingCapacity() {
		return price_carryingCapacity;
	}
	public void setPrice_carryingCapacity(double price_carryingCapacity) {
		this.price_carryingCapacity = price_carryingCapacity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public PointD getPosition() {
		return position;
	}
	public void setPosition(PointD position) {
		this.position = position;
	}
	public String getCommunication_way() {
		return communication_way;
	}
	public void setCommunication_way(String communication_way) {
		this.communication_way = communication_way;
	}
	public String getGetSalary_way() {
		return getSalary_way;
	}
	public void setGetSalary_way(String getSalary_way) {
		this.getSalary_way = getSalary_way;
	}
	public boolean isStatosCurrentlyWorking() {
		return statosCurrentlyWorking;
	}
	public void setStatosCurrentlyWorking(boolean statosCurrentlyWorking) {
		this.statosCurrentlyWorking = statosCurrentlyWorking;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
