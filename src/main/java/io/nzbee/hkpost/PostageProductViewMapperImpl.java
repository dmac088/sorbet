package io.nzbee.hkpost;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingFeeView;

public class PostageProductViewMapperImpl implements IPostageProductViewMapper {

	@Override
	public ShippingFeeView toView(Locale locale, PostageProductViewDTO dto) {
		return new ShippingFeeView(dto.getTotalPostage(), 
									   dto.getAdditionalCharge(), 
									   dto.getInsurancePremiumFee(),
									   dto.getWithPostageSurcharge(), 
									   dto.getWithHighValueSurcharge());
	}

	
	
}
