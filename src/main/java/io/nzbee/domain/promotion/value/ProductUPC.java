package io.nzbee.domain.promotion.value;


public final class ProductUPC {

	private String productUPC;

	public ProductUPC(String productUPC) {
		super();
		this.productUPC = productUPC;
	}
	
	public Boolean sameAs(ProductUPC other) {
		return this.productUPC.equals(other.productUPC);
	}

}
