package org.jcg.springboot.redis.model;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class Customer implements Serializable {

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
	@Email(message ="email should be valid")
	String email;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
