package io.nzbee.entity.promotion;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service(value="promotionEntityService")
public class PromotionEntityServiceImpl implements IPromotionEntityService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String CACHE_NAME = "promotionCache";
	
	@Autowired
	private IPromotionRepository promotionRepository;
	
	@Autowired
	private IPromotionDao promotionDao;

	@Override
	public Optional<PromotionEntity> findById(Long promotionId) {
		return promotionRepository.findById(promotionId);
	}
	
	@Override
	public Optional<PromotionEntity> findByCode(String promotionCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}", promotionCode);
		return promotionRepository.findByPromotionCode(promotionCode);
	}
	@Override
	public Optional<PromotionEntity> findByDesc(String promotionDesc) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByDesc parameters : {}", promotionDesc);
		return promotionRepository.findByAttributesPromotionDesc(promotionDesc);
	}
	
	@Override
	public Optional<PromotionDomainDTO> findByCode(String locale, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}, {}", locale, code);
		return promotionDao.findByCode(locale, code);
	}
	
	@Override
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.promotionCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.locale + \", \" + #promotion.promotionId"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.locale + \", \" + #promotion.promotionCode"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(PromotionEntity promotion) {
		promotionRepository.save(promotion);
	}

	@Override
	public void update(PromotionEntity t) {
		promotionRepository.save(t);
	}

	@Override
	public void delete(PromotionEntity t) {
		promotionRepository.delete(t);
	}

	

	

}