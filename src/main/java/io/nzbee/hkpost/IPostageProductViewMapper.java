package io.nzbee.hkpost;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingFeeView;

public interface IPostageProductViewMapper {

	ShippingFeeView toView(Locale locale, PostageProductViewDTO dto);

}
