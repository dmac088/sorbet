package io.nzbee.hkpost.shipcode;

public class ShipCodeViewDTO {

	private String code;
	
	private String nameE;
	
	private String nameC;
	
	private String nameS;
	
	private String status;

	public ShipCodeViewDTO(String code, String nameE, String nameC, String nameS, String status) {
		super();
		this.code = code;
		this.nameE = nameE;
		this.nameC = nameC;
		this.nameS = nameS;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNameE() {
		return nameE;
	}

	public void setNameE(String nameE) {
		this.nameE = nameE;
	}

	public String getNameC() {
		return nameC;
	}

	public void setNameC(String nameT) {
		this.nameC = nameT;
	}

	public String getNameS() {
		return nameS;
	}

	public void setNameS(String nameS) {
		this.nameS = nameS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
