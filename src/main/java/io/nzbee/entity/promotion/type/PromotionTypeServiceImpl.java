package io.nzbee.entity.promotion.type;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionLevelService")
public class PromotionTypeServiceImpl implements IPromotionTypeService {
	
	@Autowired
	private IPromotionTypeRepository promotionLevelRepository; 

	@Override
	public Optional<PromotionTypeEntity> findById(Long id) {
		return promotionLevelRepository.findById(id);
	}

	@Override
	public Optional<PromotionTypeEntity> findByCode(String code) {
		return promotionLevelRepository.findByPromotionLevelCode(code);
	}

	@Override
	public void save(PromotionTypeEntity t) {
		promotionLevelRepository.save(t);
	}

	@Override
	public void update(PromotionTypeEntity t) {
		promotionLevelRepository.save(t);		
	}

	@Override
	public void delete(PromotionTypeEntity t) {
		promotionLevelRepository.delete(t);
	}

}
