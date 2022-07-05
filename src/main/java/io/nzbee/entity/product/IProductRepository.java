package io.nzbee.entity.product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<ProductEntity, Long>{

	@Query(
	" SELECT p " 
	+ " FROM ProductEntity p "
	+ " LEFT JOIN FETCH p.attributes pa "
	+ " LEFT JOIN FETCH p.prices prc "
	+ " LEfT JOIN FETCH prc.type "
	+ " LEFT JOIN FETCH p.categories cats "
	+ " WHERE p.productUPC = :productCode "
	)
	Optional<ProductEntity> findByCode(String productCode);
	
}
