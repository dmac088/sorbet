package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryBrandRepository extends JpaRepository<CategoryBrandEntity, Long> {

	List<CategoryBrandEntity> findAll();
	
	Optional<CategoryBrandEntity> findByCategoryCategoryCode(String code);
	
}
