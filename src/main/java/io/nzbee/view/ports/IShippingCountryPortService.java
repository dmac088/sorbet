package io.nzbee.view.ports;
import java.util.List;

import io.nzbee.view.shipping.country.ShippingCountryView;

public interface IShippingCountryPortService extends IViewPortService<ShippingCountryView> {

	List<ShippingCountryView> findAll(String locale);

}
