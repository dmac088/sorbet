package io.nzbee.entity.bag.item.domain.regular;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.Constants;

public class RegularBagItemDomainDTOServiceImpl implements IRegularBagItemDomainDTOService {
	
	@Autowired
	private IRegularBagItemDomainDTORepository bagItemRepository;
	
	@Override
	public Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency) {
		return bagItemRepository.getNewPhysicalItem(productUPC, currency, Constants.markdownPriceCode);
	}
	
	@Override
	public Optional<RegularBagItemDomainDTO> getNewShippingItem(String currency, String shipDest, String shipType, BigDecimal bagWeight) {
		return bagItemRepository.getNewShippingItem(currency, Constants.markdownPriceCode, shipDest, shipType, bagWeight);
	}

}
