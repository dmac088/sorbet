package io.nzbee.entity.product.shipping.attribute.view;

import java.io.Serializable;
import java.util.Map;

public class ShippingProductAttributeDTO implements Serializable {
	
	private static final long serialVersionUID = 3413824252411938452L;

	public static final String ID_ALIAS 						= "prd_lcl_id";
    
    public static final String SHIPPING_TYPE_DESC_ALIAS 		= "prd_shp_typ_desc";
    
    public static final String LOCALE_CODE_ALIAS 				= "lcl_cd";
    
	private Long productAttributeId;
	
	private String shippingTypeDesc;
	
	private String locale;

	public ShippingProductAttributeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productAttributeId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.shippingTypeDesc 		= tuple[aliasToIndexMap.get(SHIPPING_TYPE_DESC_ALIAS)].toString();
	}

	public Long getProductAttributeId() {
		return productAttributeId;
	}
	
	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public void setShippingTypeDesc(String shippingTypeDesc) {
		this.shippingTypeDesc = shippingTypeDesc;
	}

	public String getLocale() {
		return locale;
	}
	
	
}
