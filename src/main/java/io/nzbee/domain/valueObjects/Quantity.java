package io.nzbee.domain.valueObjects;

public class Quantity {

	private Long quantity;
	
	public Quantity(Long quantity) {
		this.quantity = quantity;
	}


	public Long multiply(Long quantity) {
		return this.quantity * quantity;
	}
	
	public Long amount() {
		return quantity;
	}	
}
