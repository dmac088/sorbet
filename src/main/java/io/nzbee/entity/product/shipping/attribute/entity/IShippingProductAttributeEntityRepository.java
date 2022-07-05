package io.nzbee.entity.product.shipping.attribute.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShippingProductAttributeEntityRepository extends JpaRepository<ShippingProductAttributeEntity, Long> {

	List<ShippingProductAttributeEntity> findAll();

	Optional<ShippingProductAttributeEntity> findByLclCdAndProductShippingProductProductUPC(String locale, String productUPC);
	
}