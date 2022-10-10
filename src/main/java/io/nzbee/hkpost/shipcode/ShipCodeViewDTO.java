package io.nzbee.hkpost.shipcode;

public class ShipCodeViewDTO {

	private String code;
	
	private String nameE;
	
	private String nameT;
	
	private String nameS;
	
	private String status;

	public ShipCodeViewDTO(String code, String nameE, String nameT, String nameS, String status) {
		super();
		this.code = code;
		this.nameE = nameE;
		this.nameT = nameT;
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

	public String getNameT() {
		return nameT;
	}

	public void setNameT(String nameT) {
		this.nameT = nameT;
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
