package io.nzbee.entity.promotion.type;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionTypeService")
public class PromotionTypeServiceImpl implements IPromotionTypeService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private IPromotionTypeRepository promotionTypeRepository;
	
	@Override
	public Optional<PromotionTypeEntity> findById(Long id) {
		return promotionTypeRepository.findById(id);
	}

	@Override
	public Optional<PromotionTypeEntity> findByCode(String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}", code);
		return promotionTypeRepository.findByPromotionTypeCode(code);
	}

	@Override
	public void save(PromotionTypeEntity t) {
		promotionTypeRepository.save(t);
	}

	@Override
	public void update(PromotionTypeEntity t) {
		promotionTypeRepository.save(t);
	}

	@Override
	public void delete(PromotionTypeEntity t) {
		promotionTypeRepository.delete(t);
		
	}

}
