package io.nzbee.entity.product.shipping.entity.shipcode;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;


public interface IShipCodeRepository  extends JpaRepository<ShippingProductEntity, Long> {

	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.entity.shipcode.ShipCodeViewDTO("
			+ "														ps.shippingCode, " 
			+ "														attr.shippingTypeDesc "	
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productShipping ps "
			+ " JOIN ps.attributes attr "
			+ " WHERE "
			+ " attr.lclCd 					= 	:locale "
			+ " AND ps.shippingCountryCode 	= 	:desitnationCode "
			+ " AND ps.weightLimit 			>= 	:weight ")
	List<ShipCodeViewDTO> findAllForDestinationCodeAndWeight(String locale, String desitnationCode, BigDecimal weight);

	
}
