package io.nzbee.hkpost.shipcode;

public class ShipCodeViewDTO {

	private final String code;
	
	private final String nameE;
	
	private final String nameC;
	
	private final String nameS;
	
	private final String status;
	
	private final Double weightLimit;

	public ShipCodeViewDTO( String code, 
							String nameE, 
							String nameC, 
							String nameS, 
							String status,
							Double weightLimit) {
		super();
		this.code = code;
		this.nameE = nameE;
		this.nameC = nameC;
		this.nameS = nameS;
		this.status = status;
		this.weightLimit = weightLimit;
	}

	public String getCode() {
		return code;
	}

	public String getNameE() {
		return nameE;
	}

	public String getNameC() {
		return nameC;
	}

	public String getNameS() {
		return nameS;
	}

	public String getStatus() {
		return status;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}
	
}
