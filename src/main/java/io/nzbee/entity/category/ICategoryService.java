package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface ICategoryService extends IEntityService<CategoryEntity> {

	Optional<CategoryEntity> findById(Long categoryId);
	
	Optional<CategoryEntity> findByCode(String categoryCode);

	List<CategoryEntity> findAll();

}
