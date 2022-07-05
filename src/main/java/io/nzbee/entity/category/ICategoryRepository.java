package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository<T extends CategoryEntity> extends JpaRepository<T, Long>  {
	
	List<T> findAll();

	Optional<T> findByCategoryId(Long Id);
	
	Optional<T> findByCategoryCode(String code);
	
	Optional<T> findByAttributesLclCdAndAttributesCategoryDesc(String locale, String desc);
	
}
