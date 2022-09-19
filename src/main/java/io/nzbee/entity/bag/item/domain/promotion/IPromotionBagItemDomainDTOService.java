package io.nzbee.entity.bag.item.domain.promotion;

import java.util.List;

public interface IPromotionBagItemDomainDTOService {

	List<PromotionBagItemDomainDTO> getItems(String currency, String priceType, String userName);
	
}