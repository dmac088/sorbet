package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceTypeRepository extends JpaRepository<ProductPriceType, Long> {

	List<ProductPriceType> findAll();
	
	Optional<ProductPriceType> findByCode(String code);

}