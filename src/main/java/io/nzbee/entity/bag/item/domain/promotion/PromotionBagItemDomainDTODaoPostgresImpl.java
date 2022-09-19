package io.nzbee.entity.bag.item.domain.promotion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTOResultTransformer;

public class PromotionBagItemDomainDTODaoPostgresImpl implements IPromotionBagItemDomainDTODao<PromotionBagItemDomainDTO> { 

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PromotionBagItemDomainDTO> getItems(String currency, String priceType, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}", currency, priceType, userName);
		
		@SuppressWarnings("deprecation")
		Query query = 
		em.createNativeQuery("WITH RECURSIVE ancestors AS \n"
				+ "(\n"
				+ "  SELECT p.prd_id,\n"
				+ "         p.upc_cd,\n"
				+ "         t.cat_id,  \n"
				+ "			t.cat_cd, \n"
				+ "			t.cat_lvl, \n"
				+ "			t.cat_prnt_cd,  \n"
				+ "			t.cat_prnt_id,  \n"
				+ "			t.cat_typ_id, \n"
				+ "			cast('/' || cast(t.cat_id as text) || '/' as text) node \n"
				+ "  FROM mochi.category AS t \n"
				+ "    INNER JOIN mochi.product_category pc ON t.cat_id = pc.cat_id \n"
				+ "    INNER JOIN mochi.product p  ON pc.prd_id = p.prd_id\n"
				+ "  WHERE 0=0 \n"
				+ "  AND  upc_cd =  :productUPC\n"
				+ "  UNION ALL \n"
				+ "  SELECT    d.prd_id,\n"
				+ "            d.upc_cd,\n"
				+ "            t.cat_id,  \n"
				+ "			t.cat_cd,  \n"
				+ "			t.cat_lvl, \n"
				+ "			t.cat_prnt_cd,  \n"
				+ "			t.cat_prnt_id,  \n"
				+ "			t.cat_typ_id, 	\n"
				+ "			cast(d.node || CAST(t.cat_id as text) || '/' as text) node \n"
				+ "  FROM mochi.category AS t \n"
				+ "  JOIN ancestors AS d \n"
				+ "  ON d.cat_prnt_id = t.cat_id \n"
				+ ")\n"
				+ ", agg_cats as \n"
				+ "(select prd_id, upc_cd, string_agg(cat_cd, ',') as lst_cat_cd\n"
				+ "from ancestors\n"
				+ "group by prd_id, upc_cd)\n"
				+ "\n"
				+ "select \n"
				+ "  p.upc_cd as upc_cd, \n"
				+ "  '" + Constants.bagItemStatusCodeNew  + "' as bag_item_sts_cd, \n"
				+ "  prices2_.prc_val as prc_val, \n"
				+ "  physicalpr1_.weight as weight, \n"
				+ "  coalesce(stockonhan5_.soh_qty, 0)> 0 as in_stock, \n"
				+ "  1 as qty,"
				+ "  '" + Constants.regularBagItemType + "' as bag_item_typ_cd, \n"
				+ "  b.bnd_cd,\n"
				+ "  ac.lst_cat_cd, \n"
				+ "  :currency as curr\n"
				+ "from \n"
				+ "  mochi.product p \n"
				+ "  inner join mochi.product_basic physicalpr1_ on p.prd_id = physicalpr1_.prd_id \n"
				+ "  inner join mochi.price prices2_ on p.prd_id = prices2_.prd_id \n"
				+ "  inner join mochi.price_type productpri3_ on prices2_.prc_typ_id = productpri3_.prc_typ_id \n"
				+ "  inner join mochi.currency currency4_ on prices2_.ccy_id = currency4_.ccy_id \n"
				+ "  left outer join mochi.stock_on_hand stockonhan5_ on physicalpr1_.prd_id = stockonhan5_.soh_prd_id \n"
				+ "  left outer join agg_cats ac on p.prd_id = ac.prd_id \n"
				+ "  inner join mochi.brand b on p.bnd_id = b.bnd_id \n"
				+ "where \n"
				+ "  productpri3_.prc_typ_cd = :priceType \n"
				+ "  and currency4_.ccy_cd = :currency \n"
				+ "  and p.upc_cd in (:productUPC)")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("priceType", priceType)
		.setParameter("currency", currency)
		.setParameter("userName", userName)
		.setResultTransformer(new RegularBagItemDomainDTOResultTransformer());
		
		return query.getResultList();
		
	}	
	
}
