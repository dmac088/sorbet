package io.nzbee.view.product.shipping.country;

import java.util.List;
import io.nzbee.view.IViewService;

public interface IShippingCountryViewService extends IViewService<ShippingCountryView> {

	List<ShippingCountryView> findByAllShippingDestinations(String locale);

}
