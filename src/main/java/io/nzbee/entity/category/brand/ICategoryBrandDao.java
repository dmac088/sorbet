package io.nzbee.entity.category.brand;

import java.util.Optional;

public interface ICategoryBrandDao {
	
	Optional<CategoryBrandEntity> findById(Long id);

	void save(CategoryBrandEntity t);

}
