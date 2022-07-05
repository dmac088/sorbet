package io.nzbee.entity.product.shipping.view;

import java.math.BigDecimal;

public class ShippingProductViewDTO  {
	
	private final String productUPC;
	
	private final String shippingDestinationCode;
	
	private final String shippingDestinationDesc;
	
	private final String shippingTypeCode;
	
	private final String shippingTypeDesc;
	
	private final BigDecimal weightLimit;
    
    private final BigDecimal weightFrom;
    
    private final BigDecimal weightTo;
    
	public ShippingProductViewDTO(String productUPC, 
								  String shippingDestinationCode, 
								  String shippingDestinationDesc,
								  String shippingTypeCode, 
								  String shippingTypeDesc, 
								  BigDecimal weightLimit, 
								  BigDecimal weightFrom, 
								  BigDecimal weightTo) {
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

	public BigDecimal getWeightLimit() {
		return weightLimit;
	}

	public BigDecimal getWeightFrom() {
		return weightFrom;
	}

	public BigDecimal getWeightTo() {
		return weightTo;
	}

	public String getProductUPC() {
		return productUPC;
	}
    
}

