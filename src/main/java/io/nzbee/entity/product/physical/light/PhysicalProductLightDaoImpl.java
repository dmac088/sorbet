package io.nzbee.entity.product.physical.light;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;

@Component(value = "physicalProductLightDao")
public class PhysicalProductLightDaoImpl implements IPhysicalProductLightDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;


	@Override
	public PhysicalProductLightDTO findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public PhysicalProductLightDTO findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = PhysicalProductLightDTOServiceImpl.CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #pageable.getPageSize() + \", \" + #pageable.getOffset() + \", \" + #orderby")
			}
	)
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode,
			Pageable pageable, String orderby) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : {}, {}, {}, {}, {}", locale, currency,
				rootCategoryCode, pageable, orderby);

		Query query = em.createNativeQuery(this.constructSQL(false, false, false, false, false, true, false, ""))

				.setParameter("rootCategoryCode", rootCategoryCode)
				.setParameter("locale", locale)
				.setParameter("currency", currency);

		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();

		query = em.createNativeQuery(this.constructSQL(false, false, false, false, false, false, true, getOrderby(orderby)))

				.setParameter("rootCategoryCode", rootCategoryCode)
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("limit", pageable.getPageSize())
				.setParameter("offset", pageable.getOffset());

		query.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(PhysicalProductLightDTO.class));

		List<PhysicalProductLightDTO> results = query.getResultList();

		return new PageImpl<PhysicalProductLightDTO>(results, pageable, total);
	}

	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = PhysicalProductLightDTOServiceImpl.CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page + \", \" + #size + \", \" + #orderby")
			}
	)
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String orderby) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
				locale, currency, rootCategoryCode, categoryCodes.getCodes(), brandCodes.getCodes(), tagCodes.getCodes(),
				maxPrice, page, size, orderby);
		
		Query query = em
				.createNativeQuery(this.constructSQL(false,
													 !categoryCodes.getCodes().isEmpty(),
													 !brandCodes.getCodes().isEmpty(),
													 !tagCodes.getCodes().isEmpty(),
													 !(maxPrice == null),
													 true,
													 false,
													 ""))
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("rootCategoryCode", rootCategoryCode);

		if (!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}

		if (!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}

		if (!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}

		if (!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}

		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();

		query = em
				.createNativeQuery(	 this.constructSQL(false,
									 !categoryCodes.getCodes().isEmpty(),
									 !brandCodes.getCodes().isEmpty(),
									 !tagCodes.getCodes().isEmpty(),
									 !(maxPrice == null),
									 false,
									 true,
									 getOrderby(orderby)))
				
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("rootCategoryCode", rootCategoryCode);

		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		// these should contain default values for these parameters
		query
				.setParameter("limit", pageable.getPageSize())
				.setParameter("offset", pageable.getOffset());

		if (!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}

		if (!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}

		if (!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}

		if (!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}

		query.unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(PhysicalProductLightDTO.class));

		List<PhysicalProductLightDTO> results = query.getResultList();

		return new PageImpl<PhysicalProductLightDTO>(results, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), total);
	}

	private String constructSQL(boolean hasProductCodes,
								boolean hasCategoryCodes,
								boolean hasBrandCodes,
								boolean hasTagCodes,
								boolean hasPrice,
								boolean countOnly,
								boolean offset,
								String 	orderby) {

		String sql = "WITH recursive descendants AS" + 
		"(" + 
		"       SELECT t.cat_id," + 
		"              t.cat_cd," + 
		"              t.cat_lvl," + 
		"              t.cat_prnt_id," + 
		"              t.cat_prnt_cd," + 
		"              t.cat_typ_id," + 
		"              cast('/'" + 
		"                     || cast(t.cat_id AS text)" + 
		"                     || '/' AS text) node" + 
		"       FROM   mochi.category         AS t" + 
		"       WHERE  0=0" + 
		"       AND    t.cat_cd = :rootCategoryCode " + 
		"       UNION ALL" + 
		"       SELECT t.cat_id," + 
		"              t.cat_cd," + 
		"              t.cat_lvl," + 
		"              t.cat_prnt_id," + 
		"              t.cat_prnt_cd," + 
		"              t.cat_typ_id," + 
		"              cast(d.node" + 
		"                     || cast(t.cat_id AS text)" + 
		"                     || '/' AS text) node" + 
		"       FROM   mochi.category         AS t" + 
		"       JOIN   descendants            AS d" + 
		"       ON     t.cat_prnt_id = d.cat_id )" + 
		"" + 
		"SELECT " +
		((countOnly) 
		? "count(distinct physicalpr0_1_.upc_cd) as product_count " 
		:
		"		physicalpr0_1_.upc_cd                 AS productUPC," + 
		"       attr.prd_desc                 		  AS productDesc," + 
		"       attributes3_.bnd_desc                 AS brandDesc," + 
		"       MAX(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = '" + Constants.retailPriceCode + "'" +
		"			  THEN prices6_.prc_val" + 
		"             ELSE 0" + 
		"           END)                              AS retailPrice," + 
		"       MAX(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = '" + Constants.markdownPriceCode + "'" +
		"			  THEN prices6_.prc_val" + 
		"             ELSE 0" + 
		"           END)                              AS markdownPrice," + 
		"       COALESCE(stockonhan5_.soh_qty, 0) > 0 AS inStock," + 
		"       attr.prd_img_pth              AS productImage ") + 
		"FROM   mochi.product_basic physicalpr0_" + 
		"	   INNER JOIN mochi.product_category pc " + 
		"	   		   ON physicalpr0_.prd_id = pc.prd_id" + 
		"	   INNER JOIN descendants d" + 
		"	   			ON pc.cat_id = d.cat_id" + 
		"       INNER JOIN mochi.product physicalpr0_1_" + 
		"               ON physicalpr0_.prd_id = physicalpr0_1_.prd_id" + 
		"       INNER JOIN mochi.product_attr_lcl attr" + 
		"               ON physicalpr0_.prd_id = attr.prd_id" + 
		"       INNER JOIN mochi.brand brandentit2_" + 
		"               ON physicalpr0_1_.bnd_id = brandentit2_.bnd_id" + 
		"       INNER JOIN mochi.brand_attr_lcl attributes3_" + 
		"               ON brandentit2_.bnd_id = attributes3_.bnd_id" + 
		"       INNER JOIN mochi.product_status productsta4_" + 
		"               ON physicalpr0_1_.prd_sts_id = productsta4_.prd_sts_id" + 
		"       LEFT OUTER JOIN mochi.stock_on_hand stockonhan5_" + 
		"                    ON physicalpr0_.prd_id = stockonhan5_.soh_prd_id" + 
		"       INNER JOIN mochi.price prices6_" + 
		"               ON physicalpr0_.prd_id = prices6_.prd_id" + 
		"       INNER JOIN mochi.price_type productpri7_" + 
		"               ON prices6_.prc_typ_id = productpri7_.prc_typ_id" + 
		"       INNER JOIN mochi.currency currency8_" + 
		"               ON prices6_.ccy_id = currency8_.ccy_id" + 
		"			   " + 
		
		((hasTagCodes)
		? 	"	   INNER JOIN mochi.product_tag pt " + 
		"	   		   ON physicalpr0_.prd_id = pt.prd_id" + 
		"			   " + 
		"	   INNER JOIN mochi.tag t" + 
		"	   		   ON pt.tag_id = t.tag_id" + 
		"	   		   AND (t.tag_cd in :tagCodes)"  
		: "") +
		"			   " + 
		"WHERE  0=0 " +
		"		AND attr.lcl_cd = :locale" + 
		"       AND attributes3_.lcl_cd = :locale" + 
		"       AND currency8_.ccy_cd = :currency" + 
		"	    AND productsta4_.prd_sts_cd = '" + Constants.activeSKUCode + "'" +
		"	    " + 
		
		((hasBrandCodes) 
		? " AND (brandentit2_.bnd_cd in :brandCodes)"
		: "") +
		"	   " + 
		
		((hasProductCodes) 
		? "	AND (physicalpr0_1_.upc_cd IN :productCodes)"
		: "") + 
		"	   " + 
		
		((hasCategoryCodes)
		? "	AND (d.cat_cd in :categoryCodes)" 
		: "") +
		"	   " + 
		
		((hasPrice)
		? " AND prices6_.prc_val <= :maxPrice " + 
		"	AND productpri7_.prc_typ_cd = '" + Constants.markdownPriceCode + "'"
		: "") +
		"	" + 
		((!countOnly)
		? "GROUP  BY physicalpr0_1_.upc_cd," + 
		"          attr.prd_desc," + 
		"          attributes3_.bnd_desc," + 
		"          attr.prd_img_pth," + 
		"          stockonhan5_.soh_qty " + 
		"		  " + 
		((countOnly) 
		? " " 
		: 	" ORDER BY " + orderby) +
			((offset) 
			? " LIMIT :limit OFFSET :offset " 
			: "")
		: "");
		
		return sql;
	}
	
	private String getOrderby(String param) {
		LOGGER.debug("getOrderby(" + param + ")");
		switch (param) {
			case "nameAsc":
				return "lower(attr.prd_desc) asc";
			case "nameDesc":
				return "lower(attr.prd_desc) desc";
			case "priceAsc":
				return "5 asc";
			case "priceDesc":
			  	return "5 desc";
			default:
				return "lower(prd_desc) asc";
			}
	}




}
