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

public class BagItemDomainDTODaoImpl implements IRegularBagItemDomainDTODao<RegularBagItemDomainDTO>, IShippingBagItemDomainDTODao<ShippingBagItemDomainDTO> {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<RegularBagItemDomainDTO> getNewItem(String productUPC, String currency, String priceType) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}", productUPC, currency, priceType);
		
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
				+ "  and p.upc_cd = :productUPC")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("priceType", priceType)
		.setParameter("currency", currency)
		.setParameter("productUPC", productUPC)
		.setResultTransformer(new RegularBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((RegularBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<ShippingBagItemDomainDTO> getNewItem(String currency, String priceType, String shipDest, String shipType, BigDecimal bagWeight) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getNewItem, with parameters {}, {}, {}, {}, {}", currency, priceType, shipDest, shipType, bagWeight);
		
		@SuppressWarnings("deprecation")
		Query query = em.createNativeQuery(
				"select \n"
				+ "  productent0_.upc_cd as upc_cd, \n"
				+ "  '" + Constants.bagItemStatusCodeNew + "' as bag_item_sts_cd, \n"
				+ "  prices2_.prc_val as prc_val, \n"
				+ "  '" + Constants.shippingBagItemType + "' as bag_item_typ_cd,\n"
				+ "  b.bnd_cd,\n"
				+ "  string_agg(c.cat_cd, ',') as lst_cat_cd,\n"
				+ "  :currency as curr\n"
				+ "from \n"
				+ "  mochi.product productent0_ \n"
				+ "  inner join mochi.product_shipping shippingpr1_ on productent0_.prd_id = shippingpr1_.prd_id \n"
				+ "  inner join mochi.price prices2_ on productent0_.prd_id = prices2_.prd_id \n"
				+ "  inner join mochi.price_type productpri3_ on prices2_.prc_typ_id = productpri3_.prc_typ_id \n"
				+ "  inner join mochi.currency currency4_ on prices2_.ccy_id = currency4_.ccy_id \n"
				+ "  inner join mochi.brand b on productent0_.bnd_id = b.bnd_id\n"
				+ "  left join mochi.product_category pc on productent0_.prd_id = pc.prd_id\n"
				+ "  left join mochi.category c on pc.cat_id = c.cat_id\n"
				+ "where 0=0\n"
				+ "  and productpri3_.prc_typ_cd 	= :priceType \n"
				+ "  and currency4_.ccy_cd 			= :currency \n"
				+ "  and shippingpr1_.shp_typ_cd   	= :shipType \n"
				+ "  and shippingpr1_.shp_ctry_cd  	= :shipDest \n"
				+ "  and coalesce(:bagWeight, 0.1) 	between shp_wgt_frm and shp_wgt_to\n"
				+ "group by \n"
				+ "    productent0_.upc_cd, \n"
				+ "    prices2_.prc_val, \n"
				+ "    b.bnd_cd")
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("currency", currency)
		.setParameter("priceType", priceType)
		.setParameter("shipDest", shipDest)
		.setParameter("shipType", shipType)
		.setParameter("bagWeight", bagWeight)
		.setResultTransformer(new ShippingBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ShippingBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<ShippingBagItemDomainDTO> getItem(String currency, String priceType, String productUPC) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getItem, with parameters {}, {}, {}", currency, priceType, productUPC);
		
		@SuppressWarnings("deprecation")
		Query query = em.createNativeQuery(
				"select \n"
				+ "  productent0_.upc_cd as upc_cd, \n"
				+ "  '" + Constants.bagItemStatusCodeNew + "' as bag_item_sts_cd, \n"
				+ "  prices2_.prc_val as prc_val, \n"
				+ "  '" + Constants.shippingBagItemType + "' as bag_item_typ_cd,\n"
				+ "  b.bnd_cd,\n"
				+ "  string_agg(c.cat_cd, ',') as lst_cat_cd,\n"
				+ "  :currency as curr\n"
				+ "from \n"
				+ "  mochi.product productent0_ \n"
				+ "  inner join mochi.product_shipping shippingpr1_ on productent0_.prd_id = shippingpr1_.prd_id \n"
				+ "  inner join mochi.price prices2_ on productent0_.prd_id = prices2_.prd_id \n"
				+ "  inner join mochi.price_type productpri3_ on prices2_.prc_typ_id = productpri3_.prc_typ_id \n"
				+ "  inner join mochi.currency currency4_ on prices2_.ccy_id = currency4_.ccy_id \n"
				+ "  inner join mochi.brand b on productent0_.bnd_id = b.bnd_id\n"
				+ "  left join mochi.product_category pc on productent0_.prd_id = pc.prd_id\n"
				+ "  left join mochi.category c on pc.cat_id = c.cat_id\n"
				+ "where 0=0\n"
				+ "  and productpri3_.prc_typ_cd 	= :priceType\n"
				+ "  and currency4_.ccy_cd 			= :currency\n"
				+ "  and productent0_.upc_cd 		= :productUPC\n"
				+ "group by \n"
				+ "    productent0_.upc_cd, \n"
				+ "    prices2_.prc_val, \n"
				+ "    b.bnd_cd")
		
		
		.unwrap(org.hibernate.query.Query.class)
		.setParameter("currency", currency)
		.setParameter("priceType", priceType)
		.setParameter("productUPC", productUPC)
		.setResultTransformer(new ShippingBagItemDomainDTOResultTransformer());
		
		try {
			return Optional.ofNullable((ShippingBagItemDomainDTO) query.getSingleResult());
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	
	
}
