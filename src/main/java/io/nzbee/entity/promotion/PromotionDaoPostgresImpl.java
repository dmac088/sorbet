package io.nzbee.entity.promotion;

import java.util.List;
import javax.persistence.EntityManager;
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
	
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public List<PromotionDomainDTO> findShippingPromotions() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findShippingPromotion()");
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
							  "WITH promo_master AS (\n"
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
							+ "	promo.prm_act,\n"
							+ "    promo.prm_trg_rq,\n"
							+ "    disc.disc_pctg,\n"
							+ "    valdisc.bag_val_tld,\n"
							+ "    valdisc.bag_disc_pctg,\n"
							+ "    valdisc.bag_disc_curr,\n"
							+ "    valdisc.bag_disc_dir\n"
							+ "    \n"
							+ "  FROM \n"
							+ "    mochi.promotion promo \n"
							+ "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							+ "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							+ "    LEFT JOIN mochi.promotion_bngn bngn ON promo.prm_id = bngn.prm_id\n"
							+ "    LEFT JOIN mochi.promotion_disc disc ON promo.prm_id = disc.prm_id\n"
							+ "    LEFT JOIN mochi.promotion_valdisc valdisc ON promo.prm_id = valdisc.prm_id\n"
							+ ")\n"
							+ "SELECT \n"
							+ "  promo.prm_cd, \n"
							+ "  promo.prm_st_dt, \n"
							+ "  promo.prm_en_dt, \n"
							+ "  promomec.prm_mec_cd,\n"
							+ "  promotyp.prm_typ_cd,\n"
							+ "  pmc.bngn_buy_qty,\n"
							+ "  pmc.bngn_disc_pctg,\n"
							+ "  pmc.disc_pctg,\n"
							+ "  pmc.bag_val_tld,\n"
							+ "  pmc.bag_disc_pctg,\n"
							+ "  pmc.bag_disc_curr,\n"
							+ "  pmc.bag_disc_dir,\n"
							+ "  pmc.prm_act,\n"
							+ "  pmc.prm_trg_rq,\n"
							+ "  coalesce(pmc.prm_trg_cd, '') as prm_trg_cd\n"
							+ "FROM mochi.promotion promo \n"
							+ "  LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							+ "  LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							+ "  LEFT JOIN promo_master as pmc ON promo.prm_id = pmc.prm_id\n"
							+ "  \n"
							+ "WHERE promotyp.prm_typ_cd = :shippingPromotion\n")
							 .setParameter("shippingPromotion", Constants.promotionTypeShipping);
					
					query.unwrap(org.hibernate.query.Query.class)
					.setResultTransformer(new PromotionDTOResultTransformer());
					
		return query.getResultList();
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public List<PromotionDomainDTO> findBagPromotions() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findShippingPromotion()");
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
						  "--bag promotions\n"
						+ "--arbitrary discounts only at this stage\n"
						+ "WITH promo_master AS (\n"
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
						+ "	promo.prm_act,\n"
						+ "    promo.prm_trg_rq,\n"
						+ "    disc.disc_pctg,\n"
						+ "    valdisc.bag_val_tld,\n"
						+ "    valdisc.bag_disc_pctg,\n"
						+ "    valdisc.bag_disc_curr,\n"
						+ "    valdisc.bag_disc_dir\n"
						+ "    \n"
						+ "  FROM \n"
						+ "    mochi.promotion promo \n"
						+ "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
						+ "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
						+ "    LEFT JOIN mochi.promotion_bngn bngn ON promo.prm_id = bngn.prm_id\n"
						+ "    LEFT JOIN mochi.promotion_disc disc ON promo.prm_id = disc.prm_id\n"
						+ "    LEFT JOIN mochi.promotion_valdisc valdisc ON promo.prm_id = valdisc.prm_id\n"
						+ ")\n"
						+ "SELECT \n"
						+ "  promo.prm_cd, \n"
						+ "  promo.prm_st_dt, \n"
						+ "  promo.prm_en_dt, \n"
						+ "  promomec.prm_mec_cd,\n"
						+ "  promotyp.prm_typ_cd,\n"
						+ "  pmc.bngn_buy_qty,\n"
						+ "  pmc.bngn_disc_pctg,\n"
						+ "  pmc.disc_pctg,\n"
						+ "  pmc.bag_val_tld,\n"
						+ "  pmc.bag_disc_pctg,\n"
						+ "  pmc.bag_disc_curr,\n"
						+ "  pmc.bag_disc_dir,\n"
						+ "  pmc.prm_act,\n"
						+ "  pmc.prm_trg_rq,\n"
						+ "  coalesce(pmc.prm_trg_cd, '') as prm_trg_cd\n"
						+ "FROM mochi.promotion promo \n"
						+ "  LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
						+ "  LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
						+ "  LEFT JOIN promo_master as pmc ON promo.prm_id = pmc.prm_id\n"
						+ "WHERE promotyp.prm_typ_cd = :bagPromotion" 
						).setParameter("bagPromotion", Constants.promotionTypeBag);
					
		query.unwrap(org.hibernate.query.Query.class)
					.setResultTransformer(new PromotionDTOResultTransformer());
					
		return query.getResultList();
	}
	

	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public List<PromotionDomainDTO> findItemPromotion(String itemUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll()");
		
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
							+ "    AND p.upc_cd = :itemUPC \n"
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
							+ "	   promo.prm_act,\n"
							+ "    promo.prm_trg_rq,\n"
							+ "    disc.disc_pctg,\n"
							+ "    valdisc.bag_val_tld,\n"
							+ "    valdisc.bag_disc_pctg,\n"
							+ "    valdisc.bag_disc_curr,\n"
							+ "    valdisc.bag_disc_dir\n"
							+ "    \n"
							+ "  FROM \n"
							+ "    mochi.promotion promo \n"
							+ "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							+ "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							+ "    LEFT JOIN mochi.promotion_bngn bngn ON promo.prm_id = bngn.prm_id\n"
							+ "    LEFT JOIN mochi.promotion_disc disc ON promo.prm_id = disc.prm_id\n"
							+ "    LEFT JOIN mochi.promotion_valdisc valdisc ON promo.prm_id = valdisc.prm_id\n"
							+ ") \n"
							+ "--product\n"
							+ "--only bngn promotions at this stage\n"
							+ "SELECT \n"
							+ "  pmp.prm_cd, \n"
							+ "  pmp.prm_st_dt, \n"
							+ "  pmp.prm_en_dt, \n"
							+ "  pmp.prm_mec_cd,\n"
							+ "  pmp.prm_typ_cd,\n"
							+ "  pmp.bngn_buy_qty,\n"
							+ "  pmp.bngn_disc_pctg,\n"
							+ "  pmp.disc_pctg,\n"
							+ "  pmp.bag_val_tld,\n"
							+ "  pmp.bag_disc_pctg,\n"
							+ "  pmp.bag_disc_curr,\n"
							+ "  pmp.bag_disc_dir,\n"
							+ "  pmp.prm_act,\n"
							+ "  pmp.prm_trg_rq,\n"
							+ "  coalesce(pmp.prm_trg_cd, '') as prm_trg_cd\n"
							+ "FROM \n"
							+ "  mochi.product prod \n"
							+ "  INNER JOIN mochi.product_promotion prodpromo ON prod.prd_id = prodpromo.prd_id \n"
							+ "  INNER JOIN promo_master as pmp ON prodpromo.prm_id = pmp.prm_id \n"
							+ "  WHERE \n"
							+ "  prod.upc_cd 		 = :itemUPC \n"
							+ "  AND pmp.prm_typ_cd = :productPromotion\n"
							+ "UNION\n"
							+ "--brand\n"
							+ "SELECT \n"
							+ "  pmp.prm_cd, \n"
							+ "  pmp.prm_st_dt, \n"
							+ "  pmp.prm_en_dt, \n"
							+ "  pmp.prm_mec_cd,\n"
							+ "  pmp.prm_typ_cd,\n"
							+ "  pmp.bngn_buy_qty,\n"
							+ "  pmp.bngn_disc_pctg,\n"
							+ "  pmp.disc_pctg,\n"
							+ "  pmp.bag_val_tld,\n"
							+ "  pmp.bag_disc_pctg,\n"
							+ "  pmp.bag_disc_curr,\n"
							+ "  pmp.bag_disc_dir,\n"
							+ "  pmp.prm_act,\n"
							+ "  pmp.prm_trg_rq,\n"
							+ "  coalesce(pmp.prm_trg_cd, '') as prm_trg_cd\n"
							+ "FROM \n"
							+ "  mochi.product prod\n"
							+ "  INNER JOIN mochi.brand_promotion brandpromo ON prod.bnd_id = brandpromo.bnd_id \n"
							+ "  INNER JOIN promo_master as pmp ON brandpromo.prm_id = pmp.prm_id \n"
							+ "  WHERE \n"
							+ "  prod.upc_cd 		 = :itemUPC  \n"
							+ "  AND pmp.prm_typ_cd = :productPromotion\n"
							+ "UNION \n"
							+ "--category\n"
							+ "SELECT \n"
							+ "  pmc.prm_cd, \n"
							+ "  pmc.prm_st_dt, \n"
							+ "  pmc.prm_en_dt, \n"
							+ "  pmc.prm_mec_cd,\n"
							+ "  pmc.prm_typ_cd,\n"
							+ "  pmc.bngn_buy_qty,\n"
							+ "  pmc.bngn_disc_pctg,\n"
							+ "  pmc.disc_pctg,\n"
							+ "  pmc.bag_val_tld,\n"
							+ "  pmc.bag_disc_pctg,\n"
							+ "  pmc.bag_disc_curr,\n"
							+ "  pmc.bag_disc_dir,\n"
							+ "  pmc.prm_act, \n"
							+ "  pmc.prm_trg_rq,\n"
							+ "  coalesce(pmc.prm_trg_cd, '') as prm_trg_cd\n"
							+ "FROM \n"
							+ "  ancestors ans \n"
							+ "  INNER JOIN mochi.product_category pc ON ans.cat_id = pc.cat_id \n"
							+ "  INNER JOIN mochi.category_promotion catpromo ON pc.cat_id = catpromo.cat_id \n"
							+ "  INNER JOIN promo_master as pmc ON catpromo.prm_id = pmc.prm_id\n"
							+ "  AND pmc.prm_typ_cd = :productPromotion")
				 .setParameter("itemUPC", itemUPC)
				 .setParameter("productPromotion", Constants.promotionTypeProduct);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new PromotionDTOResultTransformer());
		
		return query.getResultList();
	}



	
}
