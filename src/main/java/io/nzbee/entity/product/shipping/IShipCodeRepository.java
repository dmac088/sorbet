package io.nzbee.entity.product.shipping;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.hkpost.shipcode.ShipCodeViewDTO;


public interface IShipCodeRepository  extends JpaRepository<ShippingProductEntity, Long> {

	
	@Query(	  " SELECT new io.nzbee.hkpost.shipcode.ShipCodeViewDTO("
			+ "														pe.productUPC, " 
			+ "														attr.shippingTypeDesc, "					 
			+ "													    ps.shippingStatusCode, "
			+ "														ps.weightLimit "
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
