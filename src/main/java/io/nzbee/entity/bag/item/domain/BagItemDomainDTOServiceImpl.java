package io.nzbee.entity.bag.item.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.entity.IBagItemRepository;

public class BagItemDomainDTOServiceImpl implements IBagItemDomainDTOService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRegularBagItemDomainDTODao<RegularBagItemDomainDTO> regularBagItemDao; 
	
	@Autowired
	private IShippingBagItemDomainDTODao<ShippingBagItemDomainDTO> shippingBagItemDao; 
	
	@Autowired
	private IBagItemRepository bagItemRepository;
	
	@Override
	public Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String locale, String currency) {
		return regularBagItemDao.getNewItem(productUPC, locale, currency, Constants.markdownPriceCode);
	}
	
	@Override
	public Optional<ShippingBagItemDomainDTO> getNewShippingItem(String locale, String currency, String shipDest, String shipType) {
		return shippingBagItemDao.getNewItem(locale, currency, Constants.markdownPriceCode, shipDest, shipType);
	}
	
	@Override
	public Optional<ShippingBagItemDomainDTO> getShippingItem(String locale, String currency, String code) {
		return shippingBagItemDao.getItem(locale, currency, code);
	}
	
	@Override
	public void delete(BagItemEntity e) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete()");
		bagItemRepository.delete(e);
	}
	

}
