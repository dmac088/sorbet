package io.nzbee.entity.category.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryTypeRepository extends JpaRepository<CategoryType, Long> {
	
	List<CategoryType> findAll(); 
	 
	Optional<CategoryType> findByCategoryTypeId(Long id);
	
	Optional<CategoryType> findByCategoryTypeCode(String code);
}

