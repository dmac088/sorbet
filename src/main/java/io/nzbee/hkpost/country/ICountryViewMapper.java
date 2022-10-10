package io.nzbee.hkpost.country;

import java.util.List;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.shipping.country.ShippingCountryView;

public interface ICountryViewMapper {

	List<ShippingCountryView> toView(Locale locale, List<CountryViewDTO> list);

}
