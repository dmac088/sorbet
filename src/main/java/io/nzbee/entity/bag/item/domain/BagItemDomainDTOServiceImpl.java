package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.Constants;

public class BagItemDomainDTOServiceImpl implements IBagItemDomainDTOService {
	
	@Autowired
	private IBagItemDomainDTORepository bagItemRepository;
	
	@Override
	public Optional<BagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency) {
		return bagItemRepository.getNewPhysicalItem(productUPC, currency, Constants.markdownPriceCode);
	}
	
	@Override
	public Optional<BagItemDomainDTO> getNewShippingItem(String currency, String shipDest, String shipType, BigDecimal bagWeight) {
		return bagItemRepository.getNewShippingItem(currency, Constants.markdownPriceCode, shipDest, shipType, bagWeight);
	}
	
	@Override
	public Optional<BagItemDomainDTO> getShippingItem(String currency, String priceType, String code) {
		return bagItemRepository.getShippingItem(currency, priceType, code);
	}

}
