package org.jcg.springboot.redis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PayMentEvent implements Serializable{

	String paymentType;
    BigDecimal value;
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
