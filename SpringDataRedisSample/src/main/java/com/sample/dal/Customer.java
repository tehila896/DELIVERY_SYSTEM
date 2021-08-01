package com.sample.dal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
@Data
@NoArgsConstructor
@RedisHash("Customer")
public class Customer {
//	@JsonProperty
//	@Valid
//	@Id
	private Long id;
//	@Valid
//	@JsonProperty
	private String firstName;
//	@Valid
//	@JsonProperty
	private String lastName;
	@Valid
	@JsonProperty
	private String email;
	@Valid
	@JsonProperty
	private String paymentMethod;
	public Customer(@Valid Long id, @Valid String firstName, @Valid String lastName, @Valid String email,
			@Valid String paymentMethod) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.paymentMethod = paymentMethod;
	}

 
public Long getId() {
	return this.id;
}
  
}

