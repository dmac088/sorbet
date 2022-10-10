package io.nzbee.hkpost;

import java.util.List;

import io.nzbee.view.shipping.ShippingProductView;
import io.nzbee.view.shipping.country.ShippingCountryView;

public interface IHKPostPort {

	ShippingProductView getHKPostageFee(String countryCode, String shipCode, String weight);

	List<ShippingCountryView> getCountries(String locale, String currency);
	
}
