package io.nzbee.domain.valueObjects;

import java.math.BigDecimal;

public class Percentage {

	private BigDecimal percentage;

	public Percentage(BigDecimal percentage) {
		super();
		this.percentage = percentage;
	}
	
	public Boolean sameAs(BigDecimal other) {
		return this.percentage.equals(other);
	}

	@Override
	public String toString() {
        return percentage.toString();
    }
	
	public BigDecimal amount() {
        return percentage;
    }
	
}
