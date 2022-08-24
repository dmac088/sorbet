package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
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
	private IBagItemDomainDTODao bagItemDao;
	
	@Autowired
	private IBagItemRepository bagItemRepository;
	
	@Override
	public Optional<BagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency) {
		return bagItemDao.getNewPhysicalItem(productUPC, currency, Constants.markdownPriceCode);
	}
	
	@Override
	public Optional<BagItemDomainDTO> getNewShippingItem(String currency, String shipDest, String shipType, BigDecimal bagWeight) {
		return bagItemDao.getNewShippingItem(currency, Constants.markdownPriceCode, shipDest, shipType, bagWeight);
	}
	
	@Override
	public Optional<BagItemDomainDTO> getShippingItem(String currency, String priceType, String code) {
		return bagItemDao.getShippingItem(currency, priceType, code);
	}
	
	@Override
	public void delete(BagItemEntity e) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete()");
		bagItemRepository.delete(e);
	}
	

}
