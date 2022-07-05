package io.nzbee.view.product.shipping;

public class ShippingProductView  {
	
	private final String productUPC;
	
	private final String shippingDestinationCode;
	
	private final String shippingDestinationDesc;
	
	private final String shippingTypeCode;
	
	private final String shippingTypeDesc;
	
	private final Double weightLimit;
    
    private final Double weightFrom;
    
    private final Double weightTo;
    
    

	public ShippingProductView(String productUPC, String shippingDestinationCode, String shippingDestinationDesc,
			String shippingTypeCode, String shippingTypeDesc, Double weightLimit, Double weightFrom, Double weightTo) {
		super();
		this.productUPC = productUPC;
		this.shippingDestinationCode = shippingDestinationCode;
		this.shippingDestinationDesc = shippingDestinationDesc;
		this.shippingTypeCode = shippingTypeCode;
		this.shippingTypeDesc = shippingTypeDesc;
		this.weightLimit = weightLimit;
		this.weightFrom = weightFrom;
		this.weightTo = weightTo;
	}

	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public Double getWeightFrom() {
		return weightFrom;
	}

	public Double getWeightTo() {
		return weightTo;
	}

	public String getProductUPC() {
		return productUPC;
	}
    
}

