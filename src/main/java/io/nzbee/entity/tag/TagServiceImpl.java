package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.search.IFacetService;

@Service
public class TagServiceImpl implements ITagService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagDao tagDao;
	
	@Autowired
	private ITagRepository tagRepository;
	
	@Override
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters : {}", code);
		return tagDao.findByCode(code);
	}
	
	@Override
	public List<TagEntity> findAll() {
		return tagRepository.findAll();
	}
	
	@Override
	public List<TagEntity> findAll(Set<String> codes) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters : {}", StringUtils.join(codes));
		return tagDao.findAll(codes);
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
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.locale + \", \" + #t.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.locale + \", \" + #t.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(TagEntity t) {
		tagRepository.save(t);
	}

	@Override
	public void update(TagEntity t) {
		tagRepository.save(t);
	}

	@Override
	public void delete(TagEntity t) {
		tagRepository.delete(t);
	}

	
	
}
