package io.nzbee.entity.category.brand;

import java.util.Optional;

public interface ICategoryBrandService {

	Optional<CategoryBrandEntity> findByCode(String code);

	Optional<CategoryBrandEntity> findById(Long id);
	
}
