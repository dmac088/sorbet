package io.nzbee.resources.shipping.type;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.shipping.code.ShippingCodeView;

public class ShippingTypeResource  extends RepresentationModel<ShippingTypeResource> {

	private final ShippingCodeView data;
	
	public ShippingTypeResource(ShippingCodeView product) {
		this.data = product;
	}
	
	public ShippingCodeView getData() {
		return data;
	}
	
}
