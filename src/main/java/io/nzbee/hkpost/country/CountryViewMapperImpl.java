package io.nzbee.hkpost.country;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.product.shipping.country.ShippingCountryView;

@Component
public class CountryViewMapperImpl implements ICountryViewMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<ShippingCountryView> toView(Locale locale, List<CountryViewDTO> list) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView() with parameters : {}", locale);
		return list.stream().map(c -> { 
			ShippingCountryView scv = new ShippingCountryView();
			scv.setCountryCode(c.getHkpCtyCode());
			scv.setCountryDesc(getDesc(locale.getLanguageCode(), c));
			return scv;
		}).collect(Collectors.toList());
	}
	
	private String getDesc(String languageCode, CountryViewDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getDesc() with parameters : {}", languageCode);
		switch (languageCode) {
		case Constants.localeZHHK:
			return dto.getNameC();
		default:
			return dto.getNameE();
		}
	}

}
