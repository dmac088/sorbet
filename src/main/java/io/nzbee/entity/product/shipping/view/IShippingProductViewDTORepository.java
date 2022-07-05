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
			+ "																	ps.shippingTypeCode, "
			+ "																	attr.shippingTypeDesc, "
			+ "																	ps.weightLimit, "
			+ "																	ps.weightFrom, "
			+ "																	ps.weightTo "	
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
			+ " AND ps.shippingTypeCode 	= :shipType "
			+ " AND ps.shippingCountryCode 	= :shipDest "
			+ " AND :bagWeight between ps.weightFrom and ps.weightTo "
			)
	Optional<ShippingProductViewDTO> findByDestinationAndTypeAndBagWeight(String locale, String currency, String shipDest,
			String shipType, BigDecimal bagWeight);
	
}

 