package io.nzbee.hkpost;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.ShippingProductView;

public interface IPostageProductViewMapper {

	ShippingProductView toView(Locale locale, PostageProductViewDTO dto);

}
