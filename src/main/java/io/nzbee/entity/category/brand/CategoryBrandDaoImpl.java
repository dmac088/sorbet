package io.nzbee.entity.category.brand;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class CategoryBrandDaoImpl implements ICategoryBrandDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private ICategoryBrandRepository categoryBrandRepository;
	
	@Override
	public Optional<CategoryBrandEntity> findById(Long id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById with parameters :  categoryId = {}", id);
		return categoryBrandRepository.findById(id);
	}

	@Override
	public void save(CategoryBrandEntity t) {
		categoryBrandRepository.save(t);
		
	}

}
