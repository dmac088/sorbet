package io.nzbee.entity.product.shipping.view;

import java.math.BigDecimal;
import java.util.Optional;

import io.nzbee.domain.valueObjects.Locale;

public interface IShippingProductViewDTOService {

	Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(Locale locale, String currency,
			String shippingDestinationCode, String shippingTypeCode, BigDecimal bagWeightKg);

}
