package io.nzbee.entity.product.shipping.view;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingProductViewDTOServiceImpl implements IShippingProductViewDTOService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingProductViewDTORepository shippingProductViewDTORepository;
	
	@Override
	public Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(String locale, String currency, String shippingDestinationCode,
			String shippingTypeCode, BigDecimal bagWeightKg) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByDestinationAndTypeAndBagWeight with parameter {}, {}, {}, {}, {}", locale, currency, shippingDestinationCode, shippingTypeCode, bagWeightKg);
		return shippingProductViewDTORepository.findByDestinationAndTypeAndBagWeight(locale, currency, shippingDestinationCode, shippingTypeCode, bagWeightKg);
	}


	
}
