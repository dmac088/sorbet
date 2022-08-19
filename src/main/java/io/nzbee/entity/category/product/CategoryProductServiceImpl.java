package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	@Autowired
	private ICategoryProductRepository categoryRepository;
	
	@Override
	public Optional<CategoryProductEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Optional<CategoryProductEntity> findByCode(String shippingrootcategorycode) {
		return categoryRepository.findByCategoryCategoryCode(shippingrootcategorycode);
	}

	@Override
	public List<CategoryProductEntity> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public void save(CategoryProductEntity t) {
		categoryRepository.save(t);
	}

	@Override
	public void update(CategoryProductEntity t) {
		categoryRepository.save(t);
	}

	@Override
	public void delete(CategoryProductEntity t) {
		categoryRepository.delete(t);
	}

	
}
