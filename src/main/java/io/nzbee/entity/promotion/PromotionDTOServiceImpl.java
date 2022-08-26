package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;

import org.drools.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PromotionDTOServiceImpl implements IPromotionDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public Set<PromotionDomainDTO> findAll(String locale, Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll parameters : {}, {}", locale, StringUtils.toStringArray(codes));
		return promotionDao.findByCode(locale, code);
	}
	
}
