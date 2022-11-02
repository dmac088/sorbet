package io.nzbee.entity.product.shipping.entity.shipcode;

public class ShipCodeViewDTO {

	private final String code;
	
	private final String name;
	
	public ShipCodeViewDTO(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}
