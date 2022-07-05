package io.nzbee.entity.category.attribute;

import java.util.Optional;


public interface ICategoryAttributeService {
	
	Optional<CategoryAttributeEntity> getCategoryAttributeEN(Long id);
	
	Optional<CategoryAttributeEntity> getCategoryAttributeHK(Long id);

	Optional<CategoryAttributeEntity> getCategoryAttribute(Long id, String locale);
}
