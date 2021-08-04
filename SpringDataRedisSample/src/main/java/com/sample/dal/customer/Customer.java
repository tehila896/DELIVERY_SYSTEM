package com.sample.dal.customer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@RedisHash("Customer")
public class Customer {
	@JsonProperty
	@Valid
	@Id	
	@NotNull(message = "identity can`t be null")
	private Long id;
	@Valid
	@JsonProperty
	@NotNull(message = "firstName can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "firstName must be a string")
	private String firstName;
	@Valid
	@JsonProperty
	@NotNull(message = "lastName can`t be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message = "lastName must be a string")
	private String lastName;
	@Valid
	@JsonProperty
	@Email(message ="email should be valid")
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
  
}

