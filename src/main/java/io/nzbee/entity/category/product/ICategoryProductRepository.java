package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryProductRepository extends JpaRepository<CategoryProductEntity, Long>  {
		
	@Query(" 	SELECT cp " 
			+ " FROM CategoryProductEntity cp "
			+ " LEFT JOIN FETCH cp.category c ")
	public List<CategoryProductEntity> findAll();
	
	public Optional<CategoryProductEntity> findByCategoryCategoryCode(String categoryCode);
}

