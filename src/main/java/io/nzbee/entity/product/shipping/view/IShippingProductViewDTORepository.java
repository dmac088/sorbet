package io.nzbee.entity.product.shipping.view;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;

public interface IShippingProductViewDTORepository extends JpaRepository<ShippingProductEntity, Long> {

	

	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.view.ShippingProductViewDTO("
			+ "																	pe.productUPC, "
			+ "																	ps.shippingCountryCode, "
			+ "																	attr.shippingCountryDesc, "
			+ "																	ps.shippingCode, "
			+ "																	attr.shippingTypeDesc, "
			+ "																	ps.weightLimit "
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productShipping ps "
			+ " JOIN ps.attributes attr "
			+ " JOIN pe.prices prcs "
			+ " JOIN prcs.type typ "
			+ " JOIN prcs.currency curr "
			+ " WHERE typ.code = '" + Constants.markdownPriceCode + "'"
			+ " AND curr.code 				= :currency "
			+ " AND attr.lclCd 				= :locale "
			+ " AND ps.shippingCode 		= :shipCode "
			+ " AND ps.shippingCountryCode 	= :shipDest "
			+ " AND ps.weightLimit <= :bagWeight "
			)
	Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(String locale, String currency, String shipDest,
			String shipCode, BigDecimal bagWeight);
	
}

 