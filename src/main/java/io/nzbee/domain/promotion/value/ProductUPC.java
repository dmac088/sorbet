package io.nzbee.domain.promotion.value;


public class ProductUPC {

	private final String productUPC;

	public ProductUPC(String productUPC) {
		super();
		this.productUPC = productUPC;
	}
	
	public String getValue() {
		return productUPC;
	}

}
