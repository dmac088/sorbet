package io.nzbee.entity.promotion;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;

@Component(value = "promotionEntityDao")
public class PromotionDaoPostgresImpl implements IPromotionDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	

	@SuppressWarnings({ "deprecation"})
	@Override
	public Optional<PromotionDomainDTO> findProductPromotion(String itemUPC, String triggerCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findProductPromotion parameters : {}, {}", itemUPC, triggerCode);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
							"WITH recursive ancestors AS (\n"
							+ "  SELECT \n"
							+ "    t.cat_id, \n"
							+ "    t.cat_cd, \n"
							+ "    t.cat_lvl, \n"
							+ "    t.cat_prnt_id, \n"
							+ "    t.cat_prnt_cd, \n"
							+ "    t.cat_typ_id, \n"
							+ "    cast(\n"
							+ "      '/' || cast(t.cat_id AS text) || '/' AS text\n"
							+ "    ) node \n"
							+ "  FROM \n"
							+ "    mochi.category AS t \n"
							+ "    JOIN mochi.product_category pc ON t.cat_id = pc.cat_id \n"
							+ "    JOIN mochi.product p ON pc.prd_id = p.prd_id \n"
							+ "    AND p.upc_cd = :upc\n"
							+ "  WHERE \n"
							+ "    0 = 0 \n"
							+ "  UNION ALL \n"
							+ "  SELECT \n"
							+ "    t.cat_id, \n"
							+ "    t.cat_cd, \n"
							+ "    t.cat_lvl, \n"
							+ "    t.cat_prnt_id, \n"
							+ "    t.cat_prnt_cd, \n"
							+ "    t.cat_typ_id, \n"
							+ "    cast(\n"
							+ "      d.node || cast(t.cat_id AS text) || '/' AS text\n"
							+ "    ) node \n"
							+ "  FROM \n"
							+ "    mochi.category AS t \n"
							+ "    JOIN ancestors AS d ON t.cat_id = d.cat_prnt_id\n"
							+ "), \n"
							+ "promo_master AS (\n"
							+ "  SELECT \n"
							+ "    promo.prm_id, \n"
							+ "    promo.prm_cd, \n"
							+ "    promo.prm_st_dt, \n"
							+ "    promo.prm_en_dt, \n"
							+ "    promomec.prm_mec_cd,\n"
							+ "    promo.prm_trg_cd,\n"
							+ "    promotyp.prm_typ_cd,\n"
							+ "    bngn.buy_qty as bngn_buy_qty,\n"
							+ "    bngn.disc_pctg as bngn_disc_pctg,\n"
							+ "    \n"
							+ "  FROM \n"
							+ "    mochi.promotion promo \n"
							+ "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							+ "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							+ "    LEFT JOIN mochi.promotion_bngn bngn ON promo.prm_id = bngn.prm_id\n"
							+ "   WHERE prm_act\n"
							+ "  AND promo.prm_trg_cd = :triggerCode\n"
							+ "    and promotyp.prm_typ_cd = :typeCode\n"
							+ ") \n"
							+ "SELECT \n"
							+ "  pmp.prm_cd, \n"
							+ "  pmp.prm_st_dt, \n"
							+ "  pmp.prm_en_dt, \n"
							+ "  pmp.prm_mec_cd,\n"
							+ "  pmp.prm_typ_cd,\n"
							+ "  pmp.bngn_buy_qty,\n"
							+ "  pmp.bngn_disc_pctg,\n"
							+ "FROM \n"
							+ "  mochi.product prod \n"
							+ "  INNER JOIN mochi.product_promotion prodpromo ON prod.prd_id = prodpromo.prd_id \n"
							+ "  INNER JOIN promo_master as pmp ON prodpromo.prm_id = pmp.prm_id \n"
							+ "  WHERE prod.upc_cd = :upc\n"
							+ "UNION \n"
							+ "SELECT \n"
							+ "  pmc.prm_cd, \n"
							+ "  pmc.prm_st_dt, \n"
							+ "  pmc.prm_en_dt, \n"
							+ "  pmc.prm_mec_cd,\n"
							+ "  pmc.prm_typ_cd,\n"
							+ "  pmc.bngn_buy_qty,\n"
							+ "  pmc.bngn_disc_pctg,\n"
							+ "FROM \n"
							+ "  ancestors ans \n"
							+ "  INNER JOIN mochi.product_category pc ON ans.cat_id = pc.cat_id \n"
							+ "  INNER JOIN mochi.category_promotion catpromo ON pc.cat_id = catpromo.cat_id \n"
							+ "  INNER JOIN promo_master as pmc ON catpromo.prm_id = pmc.prm_id\n"
							+ "")
				 .setParameter("upc", itemUPC)
				 .setParameter("triggerCode", triggerCode)
				 .setParameter("typeCode", Constants.promotionTypeProduct);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new PromotionDTOResultTransformer());
		
		try {
			return Optional.ofNullable((PromotionDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@SuppressWarnings({ "deprecation"})
	@Override
	public Optional<PromotionDomainDTO> findBagPromotion(String triggerCode) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findBagPromotion parameters : {}", triggerCode);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
							"  SELECT \n"
							+ "    promo.prm_cd, \n"
							+ "    promo.prm_st_dt, \n"
							+ "    promo.prm_en_dt, \n"
							+ "    promomec.prm_mec_cd,\n"
							+ "    promo.prm_trg_cd,\n"
							+ "    promotyp.prm_typ_cd,\n"
							+ "    disc.disc_pctg\n"
							+ "  FROM \n"
							+ "    mochi.promotion promo \n"
							+ "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							+ "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							+ "    LEFT JOIN mochi.promotion_disc disc ON promo.prm_id = disc.prm_id\n"
							+ "  \n"
							+ "   WHERE prm_act\n"
							+ "  AND promo.prm_trg_cd = :triggerCode\n"
							+ "  AND promotyp.prm_typ_cd = :typeCode")
				 .setParameter("triggerCode", triggerCode)
				 .setParameter("typeCode", Constants.promotionTypeBag);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new PromotionDTOResultTransformer());
		
		try {
			return Optional.ofNullable((PromotionDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}
	
}
