package io.nzbee.entity.product.shipping.view;

import io.nzbee.view.product.shipping.ShippingProductView;

public class ShippingProductViewDTOMapperImpl implements IShippingProductViewDTOMapper {

	@Override
	public ShippingProductView toView(ShippingProductViewDTO d) {
		return new ShippingProductView(d.getProductUPC(), d.getShippingDestinationCode(),
				d.getShippingDestinationDesc(), d.getShippingTypeCode(), d.getShippingTypeDesc(),
				d.getWeightLimit().doubleValue(), d.getWeightFrom().doubleValue(), d.getWeightTo().doubleValue());
	}

}
