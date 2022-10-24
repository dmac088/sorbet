package io.nzbee.hkpost;

import java.util.List;

import io.nzbee.view.shipping.ShippingFeeView;
import io.nzbee.view.shipping.code.ShippingCodeView;
import io.nzbee.view.shipping.country.ShippingCountryView;

public interface IHKPostPort {

	List<ShippingCountryView> getCountries(String locale, String currency);

	List<ShippingCodeView> getShipCodes(String locale, String currency);

	ShippingFeeView getHKPostageFee(String locale, String currency, String countryCode, String shipCode,
			String weight);
	
}
