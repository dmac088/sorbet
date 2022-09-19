package io.nzbee.entity.bag.item.domain.promotion;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PromotionBagItemDomainDTOServiceImpl implements IPromotionBagItemDomainDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionBagItemDomianDTODaoPostgres promotionBagItemDao;
	
	@Override
	public List<PromotionBagItemDomainDTO> getItems(String currency, String priceType, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}", currency, priceType, userName);
		return promotionBagItemDao.getItems(currency, priceType, userName);
	}

}
