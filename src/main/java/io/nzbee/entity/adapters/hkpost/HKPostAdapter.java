package io.nzbee.entity.adapters.hkpost;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.hkpost.IHKPostService;
import io.nzbee.hkpost.IPostageProductViewMapper;
import io.nzbee.hkpost.country.ICountryViewMapper;
import io.nzbee.hkpost.shipcode.IShipCodeService;
import io.nzbee.hkpost.shipcode.IShipCodeViewMapper;
import io.nzbee.view.shipping.ShippingFeeView;
import io.nzbee.view.shipping.code.ShippingCodeView;
import io.nzbee.view.shipping.country.ShippingCountryView;

public class HKPostAdapter implements IHKPostPort {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IHKPostService hkPostService;
	
	@Autowired
	private ICountryViewMapper countryViewMapper;

	@Autowired
	private IShipCodeService shipCodeService;
	
	@Autowired
	private IShipCodeViewMapper shipCodeViewMapper;

	@Autowired
	private IPostageProductViewMapper productViewMapper;
	
	@Override
	public ShippingFeeView getHKPostageFee(String locale, String currency, String countryCode, String shipCode, String weight) {
		return productViewMapper.toView(Locale.localize(locale, currency), hkPostService.getHKPostageFee(countryCode, shipCode, weight));
	}

	@Override
	public List<ShippingCountryView> getCountries(String locale, String currency) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getCountries()");
		return countryViewMapper.toView(Locale.localize(locale, currency), hkPostService.getCountries());
	}

	@Override
	public List<ShippingCodeView> getShipCodes(String locale, String currency, String destinationCode, IBag b) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShipCodes()");
		
		return shipCodeViewMapper.toView(Locale.localize(locale, currency), shipCodeService.findAll(locale, destinationCode, b.getTotalWeight().amount()));
	}

}
