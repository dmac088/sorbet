package io.nzbee.hkpost;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostageResponse {

    private BigDecimal totalPostage;
    
    private BigDecimal additionalCharge;
    
    private BigDecimal insurancePremiumFee;
    
    private BigDecimal withPostageSurcharge;
    
    private BigDecimal withHighValueSurcharge;

	public BigDecimal getTotalPostage() {
		return totalPostage;
	}

	public void setTotalPostage(BigDecimal totalPostage) {
		this.totalPostage = totalPostage;
	}

	public BigDecimal getAdditionalCharge() {
		return additionalCharge;
	}

	public void setAdditionalCharge(BigDecimal additionalCharge) {
		this.additionalCharge = additionalCharge;
	}

	public BigDecimal getInsurancePremiumFee() {
		return insurancePremiumFee;
	}

	public void setInsurancePremiumFee(BigDecimal insurancePremiumFee) {
		this.insurancePremiumFee = insurancePremiumFee;
	}

	public BigDecimal getWithPostageSurcharge() {
		return withPostageSurcharge;
	}

	public void setWithPostageSurcharge(BigDecimal withPostageSurcharge) {
		this.withPostageSurcharge = withPostageSurcharge;
	}

	public BigDecimal getWithHighValueSurcharge() {
		return withHighValueSurcharge;
	}

	public void setWithHighValueSurcharge(BigDecimal withHighValueSurcharge) {
		this.withHighValueSurcharge = withHighValueSurcharge;
	}
    
	@JsonProperty("data")
    private void unpackNested(Map<String,Object> data) {
        this.totalPostage = new BigDecimal(data.get("totalPostage").toString());
        this.additionalCharge = new BigDecimal(data.get("additionalCharge").toString());
        this.insurancePremiumFee = new BigDecimal(data.get("insurancePremiumFee").toString());
        this.withPostageSurcharge = new BigDecimal(data.get("withPostageSurcharge").toString());
        this.withHighValueSurcharge = new BigDecimal(data.get("withHighValueSurcharge").toString());
    }
	
	@Override 
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	
}
