package io.nzbee.view.ports;

import java.util.List;

import io.nzbee.view.shipping.code.ShippingCodeView;
import io.nzbee.view.shipping.country.ShippingCountryView;

public interface IShippingCodePortService {

	
	List<ShippingCodeView> findAll(String locale);

}
