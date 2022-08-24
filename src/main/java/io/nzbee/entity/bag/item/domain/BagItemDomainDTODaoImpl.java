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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<BagItemDomainDTO> getNewShippingItem(String currency, String priceType, String shipDest,
			String shipType, BigDecimal bagWeight) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode, with parameters {}, {}", currency);
		
		
		@SuppressWarnings("deprecation")
		Query query = em.createNativeQuery("")
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	
	
	
}
