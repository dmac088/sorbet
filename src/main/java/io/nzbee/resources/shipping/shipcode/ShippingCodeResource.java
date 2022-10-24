package io.nzbee.resources.shipping.shipcode;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.shipping.code.ShippingCodeView;

public class ShippingCodeResource  extends RepresentationModel<ShippingCodeResource> {

	private final ShippingCodeView data;
	
	public ShippingCodeResource(ShippingCodeView product) {
		this.data = product;
	}
	
	public ShippingCodeView getData() {
		return data;
	}
	
}
