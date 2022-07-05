package io.nzbee.entity.category.brand;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBrandServiceImpl implements ICategoryBrandService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICategoryBrandRepository brandCategoryRepository;
	
	
	@Override
	public Optional<CategoryBrandEntity> findById(Long id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById with parameter {}",id);
		return brandCategoryRepository.findById(id);
	}

	@Override
	public Optional<CategoryBrandEntity> findByCode(String code) {
		return brandCategoryRepository.findByCategoryCategoryCode(code);
	}

}
