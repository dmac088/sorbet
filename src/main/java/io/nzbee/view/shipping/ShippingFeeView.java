package io.nzbee.view.shipping;

import java.math.BigDecimal;

public class ShippingFeeView  {
    
	private final String productUPC;
    
	private final BigDecimal totalPostage;
    
    private final BigDecimal additionalCharge;
    
    private final BigDecimal insurancePremiumFee;
    
    private final BigDecimal withPostageSurcharge;
    
    private final BigDecimal withHighValueSurcharge;

	public ShippingFeeView(String productUPC, BigDecimal totalPostage, BigDecimal additionalCharge, BigDecimal insurancePremiumFee,
			BigDecimal withPostageSurcharge, BigDecimal withHighValueSurcharge) {
		super();
		this.productUPC = productUPC;
		this.totalPostage = totalPostage;
		this.additionalCharge = additionalCharge;
		this.insurancePremiumFee = insurancePremiumFee;
		this.withPostageSurcharge = withPostageSurcharge;
		this.withHighValueSurcharge = withHighValueSurcharge;
	}

	public String getProductUPC() {
		return productUPC;
	}

	public BigDecimal getTotalPostage() {
		return totalPostage;
	}

	public BigDecimal getAdditionalCharge() {
		return additionalCharge;
	}

	public BigDecimal getInsurancePremiumFee() {
		return insurancePremiumFee;
	}

	public BigDecimal getWithPostageSurcharge() {
		return withPostageSurcharge;
	}

	public BigDecimal getWithHighValueSurcharge() {
		return withHighValueSurcharge;
	}
    
}

