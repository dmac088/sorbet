package io.nzbee.domain.valueObjects;

import java.math.BigDecimal;

public class Weight {

	private BigDecimal weight;
	
	public Weight(BigDecimal weight) {
		this.weight = weight;
	}


	public BigDecimal multiply(Long quantity) {
		return this.weight.multiply(new BigDecimal(quantity));
	}
	
	
	public BigDecimal amount() {
		return weight;
	}

}
