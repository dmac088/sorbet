package io.nzbee.entity.adapters.domain;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.ports.IShippingBagItemPortService;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.item.domain.IShippingBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class ShippingBagItemDomainAdapter implements IShippingBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagItemDomainDTOService bagItemDomainDTOService;
	
	@Autowired
	private IShippingBagItemDomainDTOMapper shippingBagItemDomainMapper;

	@Override
	@Transactional
	public IShippingBagItem getShippingItem(IBag b, String code, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem with parameters {}, {}, {}", locale.getLocale(), Constants.markdownPriceCode, code);
		
		ShippingBagItemDomainDTO biDto = bagItemDomainDTOService.getShippingItem(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), Constants.markdownPriceCode, code)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, code));
		
		IShippingBagItem sbi = shippingBagItemDomainMapper.toDo(b, biDto);
		
		return sbi;
	}
	


}
