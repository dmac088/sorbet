package io.nzbee.entity.category.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryTypeRepository extends JpaRepository<CategoryTypeEntity, Long> {
	
	List<CategoryTypeEntity> findAll(); 
	 
	Optional<CategoryTypeEntity> findByCategoryTypeId(Long id);
	
	Optional<CategoryTypeEntity> findByCategoryTypeCode(String code);
}

