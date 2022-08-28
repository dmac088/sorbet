package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.drools.core.util.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component(value = "promotionEntityDao")
public class PromotionDaoPostgresImpl implements IPromotionDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<PromotionDomainDTO> findAll(String locale, Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}, {}", locale, StringUtils.toStringArray(codes));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
							"SELECT " +
							"	   promo.prm_id, " +
							"	   promo.prm_cd, " +
							"      prmlcl.prm_desc, " +
							"      promo.prm_st_dt, " +
							"      promo.prm_en_dt, " +
							"	   promo.prm_mec_id, " +
							"	   promomec.prm_mec_cd, " +
							"	   promomec.prm_mec_desc, " +
							"      :locale as lcl_cd " +
							
							"FROM mochi.promotion promo " +
							
							"	INNER JOIN mochi.promotion_attr_lcl prmlcl " +
							"	ON promo.prm_id = prmlcl.prm_id " +
							"	AND prmlcl.lcl_cd = :locale " +
							
							"	INNER JOIN mochi.promotion_mechanic promomec " +
							"	ON promo.prm_mec_id =  promomec.prm_mec_id " +
							"	" +
							"WHERE promo.prm_cd = :promoCode ")
				 .setParameter("locale", locale)
				 .setParameter("promoCode", codes);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new PromotionDTOResultTransformer());
		
		return query.getResultList();
	}

}
