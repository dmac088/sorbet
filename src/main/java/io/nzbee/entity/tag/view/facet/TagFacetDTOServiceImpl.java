package io.nzbee.entity.tag.view.facet;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.buf.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value = "tagFacetService")
public class TagFacetDTOServiceImpl implements ITagFacetDTOService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagFacetDao tagDao;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : {}, {}", locale, StringUtils.join(codes.getCodes()));
		return tagDao.findAll(locale, rootCategory, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagFacetDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : {}, {}, {}", locale, currency, codes.getCodes());
		return tagDao.findAll(locale, rootCategory, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, Double maxPrice) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : locale = {}, currency = {}, categoryCode = {}, categoryCodes = {}, brandCodes = {}, maxPrice = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(brandCodes, ','), maxPrice);
		return tagDao.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #code")
	public Optional<TagFacetDTO> findByCode(String locale, String rootCategory, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters : {}, {}, {}", locale, rootCategory, code);
		return tagDao.findByCode(locale, rootCategory, code);
	}
	
	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	public String getFacetField() {
		return "product.tags.tagToken";
	}

	@Override
	public String getFacetCategory() {
		return "tag";
	}


	@Override
	public Optional<TagFacetDTO> findByCode(String locale, String code) {
		return this.findByCode(locale, Constants.defaultProductRootCategoryCode, code);
	}

	@Override
	public List<TagFacetDTO> findAll(String locale, StringCollectionWrapper codes) {
		return this.findAll(locale, Constants.defaultProductRootCategoryCode, codes);
	}	

}
