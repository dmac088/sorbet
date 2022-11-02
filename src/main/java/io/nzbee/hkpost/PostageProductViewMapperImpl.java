package io.nzbee.hkpost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingFeeView;

public class PostageProductViewMapperImpl implements IPostageProductViewMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public ShippingFeeView toView(Locale locale, PostageProductViewDTO dto, String productUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView()");
		return new ShippingFeeView(	   productUPC,
									   dto.getTotalPostage(), 
									   dto.getAdditionalCharge(), 
									   dto.getInsurancePremiumFee(),
									   dto.getWithPostageSurcharge(), 
									   dto.getWithHighValueSurcharge());
	}

	
	
}
