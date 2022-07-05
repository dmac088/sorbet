package io.nzbee.hkpost;

import java.math.BigDecimal;

public class PostageRequest {

	private String countryCode;

	private BigDecimal insuranceAmount;

	private String insuranceTypeCode;

	private String mailSize;

	private String mailType;

	private String shipCode;

	private BigDecimal weight;

	public PostageRequest() {
	}

	public PostageRequest(String ecshipUsername, String hkpId, String integratorUsername, String countryCode,
			BigDecimal insuranceAmount, String insuranceTypeCode, String mailSize, String mailType,
			String shipCode, BigDecimal weight) {
		this.countryCode = countryCode;
		this.insuranceAmount = insuranceAmount;
		this.insuranceTypeCode = insuranceTypeCode;
		this.mailSize = mailSize;
		this.mailType = mailType;
		this.shipCode = shipCode;
		this.weight = weight;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public BigDecimal getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(BigDecimal insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getInsuranceTypeCode() {
		return insuranceTypeCode;
	}

	public void setInsuranceTypeCode(String insuranceTypeCode) {
		this.insuranceTypeCode = insuranceTypeCode;
	}

	public String getMailSize() {
		return mailSize;
	}

	public void setMailSize(String mailSize) {
		this.mailSize = mailSize;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
