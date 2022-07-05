package io.nzbee.entity.product.shipping.attribute.view;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationDTO;
import io.nzbee.entity.product.shipping.attribute.entity.ShippingProductAttributeEntity;
import io.nzbee.entity.product.shipping.attribute.type.ShippingTypeDTO;

public interface IShippingProductAttributeViewRepository extends JpaRepository<ShippingProductAttributeEntity, Long> {

	@Query(	  " SELECT distinct new io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationDTO("
			+ "												sp.shippingCountryCode , "
			+ "												spa.shippingCountryDesc , "
			+ "												spa.lclCd "		
			+ ") "
			+ " FROM ShippingProductAttributeEntity spa "
			+ " JOIN spa.product sp "
			+ " WHERE spa.lclCd = :locale")
	List<ShippingDestinationDTO> findAllShippingDestiantion(String locale);
	
	
	@Query(	  " SELECT distinct new io.nzbee.entity.product.shipping.attribute.type.ShippingTypeDTO("
			+ "												sp.shippingTypeCode, "
			+ "												spa.shippingTypeDesc, "
			+ "												spa.lclCd "		
			+ ") "
			+ " FROM ShippingProductAttributeEntity spa "
			+ " JOIN spa.product sp"
			+ " WHERE spa.lclCd = :locale"
			+ " AND sp.shippingCountryCode = :shippingDestinationCode"
			+ " AND :bagWeight between sp.weightFrom AND sp.weightTo "
			)
	List<ShippingTypeDTO> findAllShippingType(String locale, String shippingDestinationCode, BigDecimal bagWeight);
}
