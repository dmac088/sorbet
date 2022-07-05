package io.nzbee.entity.category.product.view.facet;

import org.springframework.data.jpa.repository.JpaRepository;
import io.nzbee.entity.category.product.CategoryProductEntity;

public interface IProductCategoryFacetDTORepository extends JpaRepository<CategoryProductEntity, Long> {
	
	
	
}
