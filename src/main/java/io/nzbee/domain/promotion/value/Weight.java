package io.nzbee.domain.promotion.value;

import java.math.BigDecimal;

public class Weight {

	private BigDecimal weight;
	
	public Weight(BigDecimal weight) {
		this.weight = weight;
	}


	public BigDecimal multiply(Long quantity) {
		return null;
	}
	
	
	public BigDecimal amount() {
		return weight;
	}

}
