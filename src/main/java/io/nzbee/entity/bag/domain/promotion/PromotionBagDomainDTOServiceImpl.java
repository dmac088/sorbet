package io.nzbee.entity.bag.domain.promotion;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.bag.entity.BagEntity;

public class PromotionBagDomainDTOServiceImpl implements IPromotionBagDomainDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private IPromotionBagDomainDTORepository bagRepository;
	
	@Autowired 
	private IPromotionBagDomainDTODao bagDao;
	
	@Override
	public Optional<PromotionBagDomainDTO> findByCode(String locale, String currency, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}", currency, userName);
		return bagDao.findByCode(locale, currency, userName);
	}

	@Override
	public void save(BagEntity b) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		bagRepository.save(b);
	}

}