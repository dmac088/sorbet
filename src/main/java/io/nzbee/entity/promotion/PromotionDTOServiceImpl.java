package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Set;
import org.drools.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PromotionDTOServiceImpl implements IPromotionDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionDao promotionDao;
	
	@Override
	public List<PromotionDomainDTO> findAll(String locale, Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll parameters : {}, {}", locale, StringUtils.toStringArray(codes));
		return promotionDao.findAll(locale, codes);
	}
	
}
