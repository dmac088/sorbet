package io.nzbee.entity.bag.view;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;

@Component
public class BagViewDTODaoPostgresImpl implements IBagViewDTODao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<BagViewDTO> findByCode(String locale, String currency, String rootCategory, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}, {}, {}", locale, currency, userName);
		
		Query query = em.createNativeQuery(this.getSQL())
						//.setParameter("categoryCode", rootCategory)
						.setParameter("userName", userName)
						.setParameter("locale", locale)
						.setParameter("currency", currency)
						.setParameter("retailPriceCode", Constants.retailPriceCode)
						.setParameter("markdownPriceCode", Constants.markdownPriceCode)
						.setParameter("activeProductCode", Constants.activeSKUCode)
						.unwrap(org.hibernate.query.Query.class)
						.setResultTransformer(new BagViewDTOResultTransformer());
		try {
			return Optional.ofNullable((BagViewDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}

	
	private String getSQL() {

		String sql = "WITH bag_items AS ( "
				+ "  SELECT  "
				+ "    bi.bag_id,  "
				+ "    bi.bag_item_sts_id,  "
				+ "    bi.bag_item_typ_id,  "
				+ "    bi.bag_item_id,  "
				+ "    bi.qty,  "
				+ "    prd.prd_id  "
				+ "  FROM  "
				+ "    mochi.bag bag  "
				+ "    INNER JOIN mochi.party pty ON bag.pty_id = bag.pty_id  "
				+ "    INNER JOIN mochi.bag_item bi ON bi.bag_id = bag.bag_id  "
				+ "    INNER JOIN mochi.product prd ON bi.prd_id = prd.prd_id  "
				+ "    INNER JOIN security.user_ usr ON pty.pty_id = usr.pty_id  "
				+ "  WHERE  "
				+ "    usr.user_name = :userName "
				+ ")  "
				+ "select  "
				+ "  p.prd_id,  "
				+ "  p.upc_cd,  "
				+ "  p.prd_crtd_dt,  "
				+ "  p.prd_desc as prd_desc,  "
				+ "  p.prd_desc as bag_item_desc, "
				+ "  p.prd_lng_desc,  "
				+ "  p.prd_img_pth, "
				+ "  p.prd_sts_cd,  "
				+ "  p.prd_sts_desc,  "
				+ "  p.width,  "
				+ "  p.height,  "
				+ "  p.length,  "
				+ "  p.weight,  "
				+ "  bag.bag_id,  "
				+ "  p.bag_item_id,  "
				+ "  p.qty,  "
				+ "  p.bag_item_sts_cd,  "
				+ "  p.bag_item_sts_desc,  "
				+ "  rt.rle_typ_id,  "
				+ "  rt.rle_typ_desc,  "
				+ "  cust.cst_num,  "
				+ "  pty.pty_id,  "
				+ "  psn.psn_gvn_nm,  "
				+ "  psn.psn_fml_nm,  "
				+ "  usr.user_name,  "
				+ "  usr.enabled,  "
				+ "  p.retail_price,  "
				+ "  p.markdown_price,  "
				+ "  COALESCE(soh.soh_qty, 0) > 0 AS prd_in_stock,  "
				+ "  p.bag_item_typ_cd,  "
				+ "  COALESCE(sp.shp_wgt_lim, 0) AS shp_wgt_lim,  "
				+ "  COALESCE(sp.shp_wgt_frm, 0) AS shp_wgt_frm,  "
				+ "  COALESCE(sp.shp_wgt_to, 0) AS shp_wgt_to "
				+ "FROM  "
				+ "  mochi.party pty  "
				+ "  INNER JOIN mochi.person psn ON pty.pty_id = psn.pty_id  "
				+ "  INNER JOIN mochi.role rle ON pty.pty_id = rle.pty_id  "
				+ "  INNER JOIN mochi.role_type rt ON rle.rle_typ_id = rt.rle_typ_id  "
				+ "  INNER JOIN security.user_ usr ON pty.pty_id = usr.pty_id  "
				+ "  INNER JOIN mochi.customer cust ON rle.rle_id = cust.rle_id  "
				+ "  INNER JOIN mochi.bag bag ON pty.pty_id = bag.pty_id  "
				+ "  LEFT JOIN ( "
				+ "    SELECT  "
				+ "      prd.prd_id,  "
				+ "      prd.upc_cd,  "
				+ "      prd.prd_crtd_dt,  "
				+ "      coalesce(acc.width, 0) as width,  "
				+ "      coalesce(acc.height, 0) as height,  "
				+ "      coalesce(acc.length, 0) as length,  "
				+ "      coalesce(acc.weight, 0) as weight,  "
				+ "      coalesce(attr.prd_desc, '') as prd_desc,  "
				+ "      coalesce(attr.prd_lng_desc, '') as prd_lng_desc,  "
				+ "      coalesce(attr.prd_img_pth, '') as prd_img_pth,  "
				+ "      ps.prd_sts_cd,  "
				+ "      ps.prd_sts_desc,  "
				+ "      bi.bag_id,  "
				+ "      bi.bag_item_id,  "
				+ "      bi.qty,  "
				+ "      bis.bag_item_sts_cd,  "
				+ "      bis.bag_item_sts_desc,  "
				+ "      bit.bag_item_typ_cd,  "
				+ "      COALESCE(rprc.prc_val, 0) AS retail_price,  "
				+ "      COALESCE(mprc.prc_val, 0) AS markdown_price  "
				+ "    FROM  "
				+ "      bag_items bi  "
				+ "      INNER JOIN mochi.bag_item_status bis ON bi.bag_item_sts_id = bis.bag_item_sts_id  "
				+ "      INNER JOIN mochi.bag_item_type bit ON bi.bag_item_typ_id = bit.bag_item_typ_id  "
				+ "      INNER JOIN mochi.product prd ON bi.prd_id = prd.prd_id  "
				+ "      INNER JOIN mochi.product_status ps ON prd.prd_sts_id = ps.prd_sts_id  "
				+ "      AND ps.prd_sts_cd = :activeProductCode "
				+ "      LEFT JOIN mochi.product_attr_lcl attr ON prd.prd_id = attr.prd_id  "
				+ "      AND attr.lcl_cd = :locale "
				+ "      LEFT JOIN ( "
				+ "        SELECT  "
				+ "          rprc.prd_id,  "
				+ "          prc_val  "
				+ "        FROM  "
				+ "          mochi.price rprc  "
				+ "          INNER JOIN mochi.currency rcurr ON rprc.ccy_id = rcurr.ccy_id  "
				+ "          AND rcurr.ccy_cd = :currency  "
				+ "          INNER JOIN bag_items bi ON rprc.prd_id = bi.prd_id  "
				+ "          INNER JOIN mochi.price_type rpt ON rprc.prc_typ_id = rpt.prc_typ_id  "
				+ "          AND rpt.prc_typ_cd = :retailPriceCode "
				+ "      ) rprc ON prd.prd_id = rprc.prd_id  "
				+ "      LEFT JOIN ( "
				+ "        SELECT  "
				+ "          mprc.prd_id,  "
				+ "          prc_val  "
				+ "        FROM  "
				+ "          mochi.price mprc  "
				+ "          INNER JOIN mochi.currency mcurr ON mprc.ccy_id = mcurr.ccy_id  "
				+ "          AND mcurr.ccy_cd = :currency  "
				+ "          INNER JOIN bag_items bi ON mprc.prd_id = bi.prd_id  "
				+ "          INNER JOIN mochi.price_type mpt ON mprc.prc_typ_id = mpt.prc_typ_id  "
				+ "          AND mpt.prc_typ_cd = :markdownPriceCode "
				+ "      ) mprc ON prd.prd_id = mprc.prd_id  "
				+ "      LEFT JOIN mochi.product_basic acc ON prd.prd_id = acc.prd_id  "
				+ "  ) p ON bag.bag_id = p.bag_id  "
				+ "  LEFT JOIN mochi.stock_on_hand soh ON p.prd_id = soh.soh_prd_id  "
				+ "  LEFT JOIN mochi.product_shipping sp ON p.prd_id = sp.prd_id  "
				+ "WHERE  "
				+ "  0 = 0  "
				+ "  AND usr.user_name = :userName  "
				+ "ORDER BY  "
				+ "  p.bag_item_id ";
		return sql;
	}

}
