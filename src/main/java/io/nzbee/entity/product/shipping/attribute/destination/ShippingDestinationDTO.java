package io.nzbee.entity.product.shipping.attribute.destination;

import java.io.Serializable;

public class ShippingDestinationDTO implements Serializable {
	
	private static final long serialVersionUID = 3413824252411938452L;

	private String shippingDestinationCode;
	
	private String shippingDestinationDesc;
	
	private String locale;

	public ShippingDestinationDTO(String shippingDestinationCode, String shippingDestinationDesc, String locale) {
		super();
		this.shippingDestinationCode = shippingDestinationCode;
		this.shippingDestinationDesc = shippingDestinationDesc;
		this.locale = locale;
	}

	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public String getLocale() {
		return locale;
	}
	
}
