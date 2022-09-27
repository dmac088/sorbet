package io.nzbee.domain.valueObjects;

public class Quantity {

	private Long quantity;
	
	public Quantity(Long quantity) {
		this.quantity = quantity;
	}

	public Quantity add(Quantity other) {
		return new Quantity(this.amount() + other.amount());
	}
	
	public Long amount() {
		return quantity;
	}	
}
