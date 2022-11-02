package io.nzbee.entity.product.shipping.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IShippingProductRepository extends JpaRepository<ShippingProductEntity, Long> {
	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.entity.ShippingProductViewDTO("
			+ "														pe.productUPC "
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productShipping ps "
			+ " JOIN ps.attributes attr "
			+ " WHERE "
			+ " attr.lclCd 					= 	:locale "
			+ " AND ps.shippingCountryCode 	= 	:desitnationCode "
			+ " AND ps.shippingCode       	=   :shipCode ")
	ShippingProductViewDTO findOne(String locale, String desitnationCode, String shipCode);
	
	ShippingProductEntity findByShippingProductProductUPC(String upc);
}
 