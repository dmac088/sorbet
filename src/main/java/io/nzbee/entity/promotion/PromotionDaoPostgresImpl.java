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
import io.nzbee.Globals;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;

@Component(value = "promotionEntityDao")
public class PromotionDaoPostgresImpl implements IPromotionDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Globals globals;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@SuppressWarnings({ "deprecation", "unchecked"})
	@Override
	public List<IPromotionDTO> findAll() {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findShippingPromotion()");
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(
							  "WITH RECURSIVE descendants AS \n"
							  + "(\n"
							  + "  SELECT 	t.cat_id,  \n"
							  + "			t.cat_cd, \n"
							  + "			t.cat_lvl, \n"
							  + "			t.cat_prnt_cd,  \n"
							  + "			t.cat_prnt_id,  \n"
							  + "			t.cat_typ_id, \n"
							  + "			cast('/' || cast(t.cat_id as text) || '/' as text) node \n"
							  + "  FROM mochi.category AS t \n"
							  + "  WHERE 0=0 \n"
							  + "  AND t.cat_cd = :categoryRoot \n"
							  + "  UNION ALL \n"
							  + "  SELECT 	t.cat_id,  \n"
							  + "			t.cat_cd,  \n"
							  + "			t.cat_lvl, \n"
							  + "			t.cat_prnt_cd,  \n"
							  + "			t.cat_prnt_id,  \n"
							  + "			t.cat_typ_id, 	\n"
							  + "			cast(d.node || CAST(t.cat_id as text) || '/' as text) node \n"
							  + "  FROM mochi.category AS t  \n"
							  + "  JOIN descendants AS d  \n"
							  + "  ON t.cat_prnt_id = d.cat_id \n"
							  + ")\n"
							  + "SELECT \n"
							  + "    promo.prm_id, \n"
							  + "    promo.prm_cd, \n"
							  + "    promo.prm_st_dt, \n"
							  + "    promo.prm_en_dt, \n"
							  + "    promomec.prm_mec_cd,\n"
							  + "    coalesce(promo.prm_trg_cd, '') as prm_trg_cd,\n"
							  + "    promotyp.prm_typ_cd,\n"
							  + "    bngn.buy_qty as bngn_buy_qty,\n"
							  + "    bngn.disc_pctg as bngn_disc_pctg,\n"
							  + "	 promo.prm_act,\n"
							  + "    promo.prm_trg_rq,\n"
							  + "    disc.disc_pctg,\n"
							  + "    valdisc.bag_val_tld,\n"
							  + "    valdisc.bag_disc_pctg,\n"
							  + "    valdisc.bag_disc_curr,\n"
							  + "    valdisc.bag_disc_dir,\n"
							  + "    d.cat_cd as prm_cat_cd,\n"
							  + "    promo.prm_bnd_cd,\n"
							  + "    promo.prm_upc_cd\n"
							  + "    \n"
							  + "    \n"
							  + "  FROM \n"
							  + "    mochi.promotion promo \n"
							  + "    LEFT JOIN mochi.promotion_mech promomec ON promo.prm_mec_id = promomec.prm_mec_id\n"
							  + "    LEFT JOIN mochi.promotion_type promotyp ON promo.prm_typ_id = promotyp.prm_typ_id\n"
							  + "    LEFT JOIN mochi.promotion_bngn bngn ON promo.prm_id = bngn.prm_id\n"
							  + "    LEFT JOIN mochi.promotion_disc disc ON promo.prm_id = disc.prm_id\n"
							  + "    LEFT JOIN mochi.promotion_valdisc valdisc ON promo.prm_id = valdisc.prm_id\n"
							  + "    LEFT JOIN descendants d \n"
							  + "    ON promo.prm_cat_cd = d.cat_prnt_cd\n"
							  + "    OR promo.prm_cat_cd = d.cat_cd")
							 .setParameter("categoryRoot", globals.getDefaultProductRootCategoryCode() );
					
					query.unwrap(org.hibernate.query.Query.class)
					.setResultTransformer(new PromotionDTOResultTransformer());
					
		return query.getResultList();
	}
}