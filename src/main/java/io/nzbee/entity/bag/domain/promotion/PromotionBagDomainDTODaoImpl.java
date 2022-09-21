package io.nzbee.entity.bag.domain.promotion;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;

public class PromotionBagDomainDTODaoImpl implements IPromotionBagDomainDTODao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	 
	@SuppressWarnings({ "deprecation" })
	@Override
	public Optional<PromotionBagDomainDTO> findByCode(String locale, String currency, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode, with parameters {}, {}, {}", locale, currency, username);
		Query query = em.createNativeQuery(" SELECT be.bag_id, "
									+ "		  		array_to_string(be.coupons, ',') as coupons, "
									+ "		  		pty.pty_id, "
									+ "		  		pu.user_name, "
									+ "       		cst.cst_num, "
									+ "       		pu.enabled, "
									+ "       		p.upc_cd, "
									+ " 	  		bi.bag_item_id, "
									+ "       		bis.bag_item_sts_cd, "
									+ "       		prc.prc_val, "
									+ "       		(case "
									+ "				when bit.bag_item_typ_cd = '" + Constants.shippingBagItemType + "' "
									+ "				then 0 "
									+ "				else pb.weight "
									+ "				end) as weight, "
									+ "				(case "
									+ "				when bit.bag_item_typ_cd = '" + Constants.shippingBagItemType + "' "
									+ " 			then true "
									+ "             else coalesce(stk.soh_qty,0) > 0 "
									+ " 			end) as in_stock, "
									+ "       		bi.qty, "
									+ "       		bit.bag_item_typ_cd, "
									+ "       		b.bnd_cd, "
									+ "				string_agg(c.cat_cd, ',') as lst_cat_cd,"
									+ "				:currency as curr,"
									+ " 			:locale as lcl"
									+ " FROM   mochi.bag be "
									+ "       JOIN mochi.party pty "
									+ "         ON be.pty_id = pty.pty_id "
									+ "       JOIN security.user_ pu "
									+ "         ON pty.pty_id = pu.pty_id "
									+ "       JOIN mochi.role pr "
									+ "         ON pty.pty_id = pr.pty_id "
									+ "       JOIN mochi.customer cst "
									+ "         ON pr.rle_id = cst.rle_id "
									+ "       LEFT JOIN mochi.bag_item bi "
									+ "         ON be.bag_id = bi.bag_id " 
									+ "       LEFT 	JOIN mochi.product p "
									+ "         ON bi.prd_id = p.prd_id "
									+ "       LEFT JOIN mochi.brand b "
									+ "			ON p.bnd_id = b.bnd_id "
									+ "       LEFT JOIN mochi.product_category pc "
									+ " 		ON p.prd_id = pc.prd_id "
									+ " 	  LEFT JOIN mochi.category c "
									+ " 	    ON pc.cat_id = c.cat_id "
									+ "       LEFT JOIN mochi.product_basic pb "
									+ "         ON p.prd_id = pb.prd_id "
									+ "       LEFT JOIN mochi.bag_item_status bis "
									+ "         ON bi.bag_item_sts_id = bis.bag_item_sts_id "
									+ "       LEFT JOIN mochi.bag_item_type bit "
									+ "         ON bi.bag_item_typ_id = bit.bag_item_typ_id "
									+ " 		  LEFT JOIN ( "
									+ "             SELECT prc.prd_id, "
	                                + "                    prc.prc_val "
	                                + "             FROM mochi.price prc "
									+ " 	          INNER JOIN mochi.price_type typ "
	                                + "                 ON prc.prc_typ_id = typ.prc_typ_id "
	                                + "                 AND typ.prc_typ_cd = :pricetype "
									+ " 	          INNER JOIN mochi.currency curr "
	                                + "                 ON prc.ccy_id = curr.ccy_id "
	                                + "                 AND curr.ccy_cd = :currency "
	                                + " 		  ) prc "
									+ " 		  ON p.prd_id = prc.prd_id "
									+ "       LEFT JOIN mochi.stock_on_hand stk "
									+ "              ON p.prd_id = stk.soh_prd_id "
									+ " WHERE 0=0 "
									+ "       AND pu.user_name = :username "
									 +" GROUP BY be.bag_id, "
												+ "	array_to_string(be.coupons, ','), "
												+ "	pty.pty_id, "
												+ "	pu.user_name, "
												+ " cst.cst_num, "
												+ " pu.enabled, "
												+ " p.upc_cd, "
												+ " bi.bag_item_id, "
												+ " bis.bag_item_sts_cd, "
												+ " prc.prc_val, "
												+ " (case "
												+ "	when bit.bag_item_typ_cd = '" + Constants.shippingBagItemType + "' "
												+ "	then 0 "
												+ " else pb.weight "
												+ " end), "
												+ " (case "
												+ "	when bit.bag_item_typ_cd = '" + Constants.shippingBagItemType + "' "
												+ " then true "
												+ " else coalesce(stk.soh_qty,0) > 0 "
												+ " end), "
												+ " bi.qty, "
												+ " bit.bag_item_typ_cd, "
												+ " b.bnd_cd")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("username", username)
		.setParameter("pricetype", Constants.markdownPriceCode)
		.setResultTransformer(new PromotionBagDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((PromotionBagDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

}



