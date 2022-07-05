package io.nzbee.entity.product.shipping.view;

import java.math.BigDecimal;
import java.util.Optional;

public interface IShippingProductViewDTOService {

	Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(String locale, String currency,
			String shippingDestinationCode, String shippingTypeCode, BigDecimal bagWeightKg);

}
