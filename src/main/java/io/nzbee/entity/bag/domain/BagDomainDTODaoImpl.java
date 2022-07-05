package io.nzbee.entity.bag.domain;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;

public class BagDomainDTODaoImpl implements IBagDomainDTODao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	 
	@SuppressWarnings({ "deprecation" })
	@Override
	public Optional<BagDomainDTO> findByCode(String currency, String username) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode, with parameters {}, {}", currency, username);
		Query query = em.createNativeQuery(" SELECT be.bag_id, "
									+ "		  		pty.pty_id, "
									+ "		  		pu.user_name, "
									+ "       		cst.cst_num, "
									+ "       		pu.enabled, "
									+ "       		p.upc_cd, "
									+ " 	  		bi.bag_item_id, "
									+ "       		bis.bag_item_sts_cd, "
									+ "       		prc.prc_val, "
									+ "       		pb.weight, "
									+ "       		(stk.soh_qty > 0) as in_stock, "
									+ "       		bi.qty "
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
									+ "       LEFT JOIN mochi.product_basic pb "
									+ "         ON p.prd_id = pb.prd_id "
									+ "       LEFT JOIN mochi.bag_item_status bis "
									+ "         ON bi.bag_item_sts_id = bis.bag_item_sts_id "
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
									+ "       AND pu.user_name = :username ")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("currency", currency)
		.setParameter("username", username)
		.setParameter("pricetype", Constants.markdownPriceCode)
		.setResultTransformer(new BagDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((BagDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

}



