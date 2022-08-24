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

public class BagItemDomainDTODaoImpl implements IBagItemDomainDTODao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<BagItemDomainDTO> getNewPhysicalItem(String productUPC, String currency, String priceType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewPhysicalItem, with parameters {}, {}, {}", productUPC, currency, priceType);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
				" SELECT "
				+ "	pe.productUPC, "
				+ "	'" + Constants.bagItemStatusCodeNew + "',"
				+ "	prcs.priceValue, "
				+ "	pp.weightDimension, "	
				+ "	stk.stockOnHand > 0 "			
				+ " "
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
		.setParameter("pricetype", Constants.markdownPriceCode)
		.setResultTransformer(new BagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((BagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<BagItemDomainDTO> getNewShippingItem(String currency, String priceType, String shipDest,
			String shipType, BigDecimal bagWeight) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewShippingItem, with parameters {}, {}, {}, {}, {}", currency, priceType, shipDest, shipType, bagWeight);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
				" SELECT "
				+ "pe.productUPC, "
				+ "'" + Constants.bagItemStatusCodeNew + "',"
				+ "prcs.priceValue "	
				+ " "
				+ " FROM ProductEntity pe "
				+ " JOIN pe.productShipping ps "
				+ " JOIN pe.prices prcs "
				+ " JOIN prcs.type typ "
				+ " JOIN prcs.currency curr "
				+ " WHERE typ.code = :priceType "
				+ " AND curr.code 				= :currency "
				+ " AND ps.shippingTypeCode 	= :shipType "
				+ " AND ps.shippingCountryCode 	= :shipDest "
				+ " AND :bagWeight between ps.weightFrom and ps.weightTo ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("pricetype", Constants.markdownPriceCode)
		.setResultTransformer(new BagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((BagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<BagItemDomainDTO> getShippingItem(String currency, String priceType, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingItem, with parameters {}, {}, {}", currency, priceType, code);
		
		@SuppressWarnings("deprecation")
		Query query = em.createQuery(
				" SELECT pe.productUPC, "
				+ "'" + Constants.bagItemStatusCodeNew + "',"
				+ "prcs.priceValue "	
				+ " "
				+ " FROM ProductEntity pe "
				+ " JOIN pe.productShipping ps "
				+ " JOIN pe.prices prcs "
				+ " JOIN prcs.type typ "
				+ " JOIN prcs.currency curr "
				+ " WHERE typ.code = :priceType "
				+ " AND curr.code 				= :currency "
				+ " AND ps.shippingTypeCode 	= :shipType "
				+ " AND ps.shippingCountryCode 	= :shipDest "
				+ " AND :bagWeight between ps.weightFrom and ps.weightTo ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("pricetype", Constants.markdownPriceCode)
		.setResultTransformer(new BagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((BagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	
	
}
