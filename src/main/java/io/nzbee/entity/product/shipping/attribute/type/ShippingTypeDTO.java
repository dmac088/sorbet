package io.nzbee.entity.product.shipping.attribute.type;

import java.io.Serializable;

public class ShippingTypeDTO implements Serializable {
	
	private static final long serialVersionUID = 3413824252411938452L;
    
	private String shippingTypeCode;
	
	private String shippingTypeDesc;
	
	private String locale;
	
	public ShippingTypeDTO(String shippingTypeCode, String shippingTypeDesc, String locale) {
		super();
		this.shippingTypeCode = shippingTypeCode;
		this.shippingTypeDesc = shippingTypeDesc;
		this.locale = locale;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public String getLocale() {
		return locale;
	}

	
	
	
}
