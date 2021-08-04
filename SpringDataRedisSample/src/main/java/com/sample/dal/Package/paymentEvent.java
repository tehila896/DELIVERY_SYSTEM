package com.sample.dal.Package;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class paymentEvent implements Serializable {

    public String paymentType;
    public BigDecimal value;
    public Date datetime;

    public paymentEvent() {
    }

    @Override
    public String toString() {
        return this.paymentType
                + " = $" + this.value.toString()
                + " at " + this.datetime.toString();
    }
}