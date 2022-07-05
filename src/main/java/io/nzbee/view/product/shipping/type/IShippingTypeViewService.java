package io.nzbee.view.product.shipping.type;

import java.util.List;
import io.nzbee.view.IViewService;

public interface IShippingTypeViewService extends IViewService<ShippingTypeView> {

	List<ShippingTypeView> findByAllShippingTypesByDestinationAndWeight(String locale, String shippingDestinationCode,
			String userName);

}
