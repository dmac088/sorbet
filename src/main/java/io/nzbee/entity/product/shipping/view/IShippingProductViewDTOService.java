package io.nzbee.entity.product.shipping.view;

import java.util.Optional;

public interface IShippingProductViewDTOService {

	Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(String locale, String currency,
			String shippingDestinationCode, String shippingTypeCode);

}
