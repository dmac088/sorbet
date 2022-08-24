package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public interface IBagItemDomainDTORepository extends JpaRepository<BagItemEntity, String> {

	
	//we need a DAO object for these queries since we need to hydrate a one to many relationship
	//specifically we need to hydrate the promotion and discount collections
	//it's not simply a case of hydrating the bagItem
	
	@Query(	  " SELECT new io.nzbee.entity.bag.item.domain.BagItemDomainDTO("
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
	Optional<BagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency, String priceType);
	
	
	@Query(	  " SELECT new io.nzbee.entity.bag.item.domain.BagItemDomainDTO("
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
	Optional<BagItemDomainDTO> getNewShippingItem(String currency, String priceType, String shipDest, String shipType, BigDecimal bagWeight);


	@Query(	  " SELECT new io.nzbee.entity.bag.item.domain.BagItemDomainDTO("
			+ "																	pe.productUPC, "
			+ "																	'" + Constants.bagItemStatusCodeNew + "',"
			+ "																	prcs.priceValue "	
			+ ") "
			+ " FROM ProductEntity pe "
			+ " JOIN pe.productShipping ps "
			+ " JOIN pe.prices prcs "
			+ " JOIN prcs.type typ "
			+ " JOIN prcs.currency curr "
			+ " WHERE typ.code 				= :priceType "
			+ " AND curr.code 				= :currency "
			+ " AND pe.productUPC 			= :code "
			)
	Optional<BagItemDomainDTO> getShippingItem(String currency, String priceType, String code);
	
	
	
	
	
	
}