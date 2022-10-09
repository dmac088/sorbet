package io.nzbee.hkpost.country;

import java.util.List;

import io.nzbee.view.product.shipping.country.ShippingCountryView;

public interface ICountryViewMapper {

	List<ShippingCountryView> toView(CountryResponseDTO dto);

}
