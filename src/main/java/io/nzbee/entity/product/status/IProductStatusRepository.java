package io.nzbee.entity.product.status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductStatusRepository extends JpaRepository<ProductStatusEntity, Long> {
	
	Optional<ProductStatusEntity> findByProductStatusCode(String code); 
	
	List<ProductStatusEntity> findAll();
	
}
