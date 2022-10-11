package io.nzbee.hkpost;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingProductView;

public class PostageProductViewMapperImpl implements IPostageProductViewMapper {

	@Override
	public ShippingProductView toView(Locale locale, PostageProductViewDTO dto) {
		return new ShippingProductView(dto.getTotalPostage(), 
									   dto.getAdditionalCharge(), 
									   dto.getInsurancePremiumFee(),
									   dto.getWithPostageSurcharge(), 
									   dto.getWithHighValueSurcharge());
	}

	
	
}
