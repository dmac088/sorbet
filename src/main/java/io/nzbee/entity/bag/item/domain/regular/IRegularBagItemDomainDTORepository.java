package io.nzbee.entity.bag.item.domain.regular;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IRegularBagItemDomainDTORepository extends JpaRepository<BagItemEntity, String> {

	@Query(	  " SELECT new io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTO("
			+ "																	pe.productUPC, "
			+ "																	'" + Constants.bagItemStatusCodeNew + "',"
			+ "																	prcs.priceValue, "
			+ "																	pp.weightDimension, "	
			+ "																	stk.stockOnHand > 0 "			
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productPhysical pp "
			+ " JOIN pe.prices prcs "
			+ " JOIN prcs.type typ "
			+ " JOIN prcs.currency curr "
			+ " LEFT JOIN pp.stockOnHand stk "
			+ " WHERE typ.code = :priceType "
			+ " AND curr.code = :currency "
			+ " AND pe.productUPC = :productUPC "
			)
	Optional<RegularBagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency, String priceType);
	
	
	@Query(	  " SELECT new io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTO("
			+ "																	pe.productUPC, "
			+ "																	'" + Constants.bagItemStatusCodeNew + "',"
			+ "																	prcs.priceValue "	
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productShipping ps "
			+ " JOIN pe.prices prcs "
			+ " JOIN prcs.type typ "
			+ " JOIN prcs.currency curr "
			+ " WHERE typ.code = :priceType "
			+ " AND curr.code 				= :currency "
			+ " AND ps.shippingTypeCode 	= :shipType "
			+ " AND ps.shippingCountryCode 	= :shipDest "
			+ " AND :bagWeight between ps.weightFrom and ps.weightTo "
			)
	Optional<RegularBagItemDomainDTO> getNewShippingItem(String currency, String priceType, String shipDest, String shipType, BigDecimal bagWeight);
	
	
	
	
	
	
}