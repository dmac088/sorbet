package io.nzbee.entity.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component 
public class TagDaoPostgresImpl implements ITagDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	
	@Autowired
	private ITagRepository tagRepository;
	
	@Override
	public Optional<TagEntity> findById(Long id) {
		return tagRepository.findById(id);
	}

	@Override
	public List<TagEntity> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public List<TagEntity> findAll(Set<String> codes) {
		return tagRepository.findByTagCodeIn(codes);
	}
	
	@Override
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters : {}", code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<TagEntity> cq = cb.createQuery(TagEntity.class);
		
		Root<TagEntity> root = cq.from(TagEntity.class);

		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(TagEntity_.TAG_CODE), code)
		);
		
		TypedQuery<TagEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			TagEntity tag = query.getSingleResult();
			return Optional.ofNullable(tag);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}

}
