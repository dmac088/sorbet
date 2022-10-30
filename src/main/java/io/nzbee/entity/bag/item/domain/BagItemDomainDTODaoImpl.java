package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;

public class BagItemDomainDTODaoImpl implements IRegularBagItemDomainDTODao<RegularBagItemDomainDTO>, IShippingBagItemDomainDTODao<ShippingBagItemDomainDTO> {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<RegularBagItemDomainDTO> getNewItem(String locale, String currency, String productUPC, String priceType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}", productUPC, currency, priceType);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
						" SELECT "
						+ "	pe.productUPC as upc_cd, "
						+ "	'" + Constants.bagItemStatusCodeNew + "' as bag_item_sts_cd,"
						+ " '" + Constants.regularBagItemType + "' as bag_item_typ_cd,\n"
						+ "	prcs.priceValue as prc_val, "
						+ "	pp.weightDimension as weight, "	
						+ "	stk.stockOnHand > 0 as in_stock, "			
						+ " 1 as qty, "
						+ " '" + currency + "' as curr,"
						+ " '" + locale + "' as lcl"
						+ " FROM ProductEntity pe "
						+ " JOIN pe.productPhysical pp "
						+ " JOIN pe.prices prcs "
						+ " JOIN prcs.type typ "
						+ " JOIN prcs.currency curr "
						+ " LEFT JOIN pp.stockOnHand stk "
						+ " WHERE typ.code = :priceType "
						+ " AND curr.code = :currency "
						+ " AND pe.productUPC = :productUPC ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("priceType", priceType)
		.setParameter("currency", currency)
		.setParameter("productUPC", productUPC)
		.setResultTransformer(new RegularBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((RegularBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<ShippingBagItemDomainDTO> getNewItem(String locale, String currency, String priceType, String shipDest, String shipCode, BigDecimal bagWeight) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}, {}, {}", currency, priceType, shipDest, shipCode, bagWeight);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
				" SELECT "
				+ "pe.productUPC as upc_cd, "
				+ "'" + Constants.bagItemStatusCodeNew + "' as bag_item_sts_cd,"
				+ "'" + Constants.shippingBagItemType + "' as bag_item_typ_cd,\n"
				+ "prcs.priceValue as prc_val, "
				+ " '" + currency + "' as curr, "
				+ " '" + locale + "' as lcl"
				+ " FROM ProductEntity pe "
				+ " JOIN pe.productShipping ps "
				+ " JOIN pe.prices prcs "
				+ " JOIN prcs.type typ "
				+ " JOIN prcs.currency curr "
				+ " WHERE typ.code = :priceType "
				+ " AND curr.code 				= :currency "
				+ " AND ps.shippingCode 		= :shipCode "
				+ " AND ps.shippingCountryCode 	= :shipDest "
				+ " AND :bagWeight <= ps.weightLimit ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("currency", currency)
		.setParameter("priceType", priceType)
		.setParameter("shipDest", shipDest)
		.setParameter("shipCode", shipCode)
		.setParameter("bagWeight", bagWeight)
		.setResultTransformer(new ShippingBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ShippingBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<ShippingBagItemDomainDTO> getItem(String locale, String currency, String priceType, String productUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getItem, with parameters {}, {}, {}", currency, priceType, productUPC);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
				" SELECT pe.productUPC as upc_cd, "
				+ " '" + Constants.bagItemStatusCodeNew + " ' as bag_item_sts_cd,"
				+ " '" + Constants.shippingBagItemType + "'   as bag_item_typ_cd,\n"
				+ " prcs.priceValue as prc_val, "
				+ " '" + currency + "' as curr, "
				+ " '" + locale + "' as lcl"
				+ " FROM ProductEntity pe "
				+ " JOIN pe.productShipping ps "
				+ " JOIN pe.prices prcs "
				+ " JOIN prcs.type typ "
				+ " JOIN prcs.currency curr "
				+ " WHERE typ.code 		= :priceType "
				+ " AND curr.code 		= :currency "
				+ " AND pe.productUPC 	= :productUPC ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("currency", currency)
		.setParameter("priceType", priceType)
		.setParameter("productUPC", productUPC)
		.setResultTransformer(new ShippingBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ShippingBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	
	
}
