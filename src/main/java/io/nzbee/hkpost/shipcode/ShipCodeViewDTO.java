package io.nzbee.hkpost.shipcode;

import java.math.BigDecimal;

public class ShipCodeViewDTO {

	private final String code;
	
	private final String name;
	
	private final String status;
	
	private final Double weightLimit;

	public ShipCodeViewDTO( String code, 
							String name, 
							String status,
							BigDecimal weightLimit) {
		super();
		this.code = code;
		this.name = name;
		this.status = status;
		this.weightLimit = weightLimit.doubleValue();
	}

	public String getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public String getName() {
		return name;
	}
	
}
