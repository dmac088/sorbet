package io.nzbee.entity.tag.view.facet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.TagServiceImpl;

@Component 
public class TagFacetDTOPostgresDaoImpl implements ITagFacetDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
		
	

	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = TagServiceImpl.CACHE_NAME, key="#locale + \", \" + #code")
			}
	)
	public Optional<TagFacetDTO> findByCode(String locale, String rootCategory, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters : {}, {}", locale, code);
		
		Session session = em.unwrap(Session.class);
		
		List<String> ltc = Arrays.asList(code);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 !ltc.isEmpty(),
															 false,
															 false))
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("locale", locale)
				 .setParameter("tagCodes", ltc)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagFacetDTOResultTransformer());
		
		try {
			TagFacetDTO result = (TagFacetDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}

	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = TagFacetDTOServiceImpl.CACHE_NAME, key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
			}
	)
	public List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("pop call TagDaoPostgresImpl.findAll with parameters : {}, {}", locale, StringUtil.join(codes));

		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 !codes.getCodes().isEmpty(),
															 false,
															 false))
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		if(!codes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", codes.getCodes());
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagFacetDTOResultTransformer());
		
		return query.getResultList();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<TagFacetDTO> findAll(String locale, String rootCategory) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : {}, {}", locale);

		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 false))
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
				
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagFacetDTOResultTransformer());
		
		return query.getResultList();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = TagFacetDTOServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
			}
	)
	public List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, Double maxPrice) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : locale = {}, currency = {}, categoryCode = {}, categoryCodes = {}, brandCodes = {}, maxPrice = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(brandCodes, ','), maxPrice);
		
		Session session = em.unwrap(Session.class);
		
		categoryCodes.getCodes().add(categoryCode);
		
		Query query = session.createNativeQuery(constructSQL(
															 !categoryCodes.getCodes().isEmpty(),
															 !brandCodes.getCodes().isEmpty(),
															 !(maxPrice == null),
															 false,
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", categoryCode)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
			query.setParameter("currency", currency);
			query.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagFacetDTOResultTransformer());
	
		return query.getResultList();
		
	}
	
		

	private String constructSQL(
			boolean hasCategories,
			boolean hasBrands,
			boolean hasPrice,
			boolean hasTagCodes,
			boolean hasTagDescriptions,
			boolean hasTagIds
		) {
		String sql = "WITH recursive descendants AS " + 
				"( " + 
				"          SELECT    t.cat_id, " + 
				"                    t.cat_cd, " + 
				"                    t.cat_lvl, " + 
				"                    t.cat_prnt_id, " +
				"                    t.cat_prnt_cd, " + 
				"                    t.cat_typ_id, " + 
				"                    cast('/' " + 
				"                              || cast(t.cat_id AS text) " + 
				"                              || '/' AS text) node " + 
				"          FROM      mochi.category            AS t " + 
				"          WHERE     0=0 " + 
				" 		   AND t.cat_cd 	= :categoryCode " + 
				"          UNION ALL " + 
				"          SELECT t.cat_id, " + 
				"                 t.cat_cd, " + 
				"                 t.cat_lvl, " + 
				"                 t.cat_prnt_id, " +
				"                 t.cat_prnt_cd, " +
				"                 t.cat_typ_id, " + 
				"                 cast(d.node " + 
				"                        || cast(t.cat_id AS text) " + 
				"                        || '/' AS text) node " + 
				"          FROM   mochi.category         AS t " + 
				"          JOIN   descendants            AS d " + 
				"          ON     t.cat_prnt_id = d.cat_id )" + 
				", categories AS " + 
				"( " + 
				"          SELECT    coalesce(s2.cat_id,s1.cat_id) as cat_id, " +
				"		   			 coalesce(s2.cat_cd,s1.cat_cd) as cat_cd " +
				"          FROM      descendants s1 " + 
				"          LEFT JOIN descendants s2 " + 
				"          ON        s1.node <> s2.node " + 
				"          AND       LEFT(s2.node, length(s1.node)) = s1.node " +
				"		   WHERE 0=0 " + 
				((hasCategories) ? 	"AND  s1.cat_cd IN (:categoryCodes) " : "") +
				"          GROUP BY  coalesce(s2.cat_id,s1.cat_id), " +
				"		   			 coalesce(s2.cat_cd,s1.cat_cd)" +
				")" + 
				"			select t.tag_id					as tag_id,  " + 
				"				   t.tag_cd 				as tag_cd, " + 
				"				   lcl.tag_lcl_id 			as tag_lcl_id, " + 
				"				   lcl.tag_desc 			as tag_desc, " + 
				"				   lcl.lcl_cd 				as lcl_cd, 		" + 
				"				   count(distinct p.upc_cd) as object_count  " + 
				"			from categories c  " + 
				"				inner join mochi.product_category pc " + 
				"					on c.cat_id = pc.cat_id " + 
				"				 " + 
				"				inner join mochi.product p " + 
				"					on pc.prd_id = p.prd_id " + 
				"				 " + 
				"				inner join mochi.product_status ps " + 
				"					on p.prd_sts_id = ps.prd_sts_id " + 
				"					and ps.prd_sts_cd = :activeProductCode " + 
				"									  " + 
				"				inner join mochi.brand b " +
				" 					on p.bnd_id = b.bnd_id " +
							
				"				inner join mochi.product_tag pt " + 
				"					on p.prd_id = pt.prd_id " + 
				 
				"				inner join mochi.tag t " + 
				"					on pt.tag_id = t.tag_id " + 
				 
				"				inner join mochi.tag_attr_lcl lcl " + 
				"					on t.tag_id = lcl.tag_id " + 
				"					and lcl.lcl_cd = :locale  " + 
				 
				((hasPrice) ?
								"inner join  ( " +
										"	SELECT prd_id, " +  
										"		   prc_val " + 
										"		FROM mochi.price mprc " + 
										"		INNER JOIN mochi.currency mcurr " + 
									    "		ON         mprc.ccy_id = mcurr.ccy_id  " + 
										"		AND        mcurr.ccy_cd = :currency  " +
										"		INNER JOIN mochi.price_type mpt " +
										"		ON         mprc.prc_typ_id = mpt.prc_typ_id " + 
										"		AND        mpt.prc_typ_cd = :markdownPriceCode " + 
										"		) mprc  " +
								"		ON p.prd_id = mprc.prd_id  " +
								"		AND prc_val <= :maxPrice " 
								: "") +
				
				"			where 0=0  " +
				((hasBrands) ? 				" 	AND b.bnd_cd 		in 	:brandCodes " 			: "") +
				((hasTagCodes) ? 			" 	AND t.tag_cd 		in 	:tagCodes " 			: "") +
				((hasTagDescriptions) ? 	" 	AND lcl.tag_desc 	in 	:tagDescriptions " 		: "") +
				((hasTagIds) ? 	" 				AND t.tag_id 	 	in 	:tagIDs " 				: "") +
				"			group by t.tag_id,  " + 
				"				   t.tag_cd, " + 
				"				   lcl.tag_lcl_id, " + 
				"				   lcl.tag_desc, " + 
				"				   lcl.lcl_cd " +
				"			order by lcl.tag_desc ";
		
	return sql;
	}




}
