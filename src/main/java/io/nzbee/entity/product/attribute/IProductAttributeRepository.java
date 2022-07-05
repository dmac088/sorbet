package io.nzbee.entity.product.attribute;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long> {

	List<ProductAttributeEntity> findAll();

	Optional<ProductAttributeEntity> findByLclCdAndProductProductUPC(String locale, String productUPC);
}
