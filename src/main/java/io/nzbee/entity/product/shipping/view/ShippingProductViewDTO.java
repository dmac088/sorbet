package io.nzbee.entity.product.shipping.view;

public class ShippingProductViewDTO  {
	
	private final String productUPC;
	
	private final String shippingDestinationCode;
	
	private final String shippingDestinationDesc;
	
	private final String shippingTypeCode;
	
	private final String shippingTypeDesc;
	
	public ShippingProductViewDTO(String productUPC, 
								  String shippingDestinationCode, 
								  String shippingDestinationDesc,
								  String shippingTypeCode, 
								  String shippingTypeDesc) {
		super();
		this.productUPC = productUPC;
		this.shippingDestinationCode = shippingDestinationCode;
		this.shippingDestinationDesc = shippingDestinationDesc;
		this.shippingTypeCode = shippingTypeCode;
		this.shippingTypeDesc = shippingTypeDesc;
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

	public String getProductUPC() {
		return productUPC;
	}
    
}

