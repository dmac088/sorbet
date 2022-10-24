package io.nzbee.resources.shipping.shipcode;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.ProductController;
import io.nzbee.view.shipping.code.ShippingCodeView;

@Component
public class ShippingTypeResourceAssembler extends RepresentationModelAssemblerSupport<ShippingCodeView, ShippingCodeResource> {
	
	public ShippingTypeResourceAssembler() {
		super(ProductController.class, ShippingCodeResource.class);
	}

	@Override
	public ShippingCodeResource toModel(ShippingCodeView product) {
		ShippingCodeResource pr = new ShippingCodeResource(product);
		
		return pr;
	}

}
