package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICategoryRepository<CategoryEntity> categoryRepository;

	
	@Override
	public Optional<CategoryEntity> findById(Long Id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById with parameter {}", Id);
		return categoryRepository.findByCategoryId(Id);
	}

	@Override
	public Optional<CategoryEntity> findByCode(String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}", code);
		return categoryRepository.findByCategoryCode(code);
	}

	@Override
	public List<CategoryEntity> findAll() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll");
		return categoryRepository.findAll();
	}
	
	@Override
	public void save(CategoryEntity t) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save");
		categoryRepository.save(t);
	}

	@Override
	public void update(CategoryEntity t) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".update");
		categoryRepository.save(t);
	}

	@Override
	public void delete(CategoryEntity t) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete");
		categoryRepository.delete(t);
	}
}
