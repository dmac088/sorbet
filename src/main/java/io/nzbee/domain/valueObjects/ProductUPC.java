package io.nzbee.domain.valueObjects;


public final class ProductUPC {

	private String productUPC;

	public ProductUPC(String productUPC) {
		super();
		this.productUPC = productUPC;
	}
	
	public Boolean sameAs(ProductUPC other) {
		return this.productUPC.equals(other.productUPC);
	}

	@Override
	public String toString() {
        return productUPC;
    }
	
}
