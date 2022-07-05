package io.nzbee.entity.product.department;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute_;

@Component
public class DepartmentDaoImpl  implements IDepartmentDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Override
	public Optional<DepartmentEntity> findById(long id) {
		return departmentRepository.findById(id);
	}
	
	@Override
	public Optional<DepartmentDomainDTO> findById(String locale, Long id) {
		
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById parameters : {}, {}", locale, id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<DepartmentDomainDTO> cq = cb.createQuery(DepartmentDomainDTO.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.select(cb.construct(		DepartmentDomainDTO.class, 
							   		root.get(DepartmentEntity_.departmentId),
							   		root.get(DepartmentEntity_.departmentCode),
							   		attribute.get(DepartmentAttribute_.departmentDesc),
							   		attribute.get(DepartmentAttribute_.lclCd)
		));
		
		cq.where(cb.and(
				 cb.equal(root.get(DepartmentEntity_.departmentId), id),
				 cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale))
		);

		
		try {
			DepartmentDomainDTO department = em.createQuery(cq).getSingleResult();
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<DepartmentDomainDTO> findByCode(String locale, String code) {
		
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode parameters : {}, {}, {}", locale, code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<DepartmentDomainDTO> cq = cb.createQuery(DepartmentDomainDTO.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.select(cb.construct(		DepartmentDomainDTO.class, 
							   		root.get(DepartmentEntity_.departmentId),
							   		root.get(DepartmentEntity_.departmentCode),
							   		attribute.get(DepartmentAttribute_.departmentDesc),
							   		attribute.get(DepartmentAttribute_.lclCd)
		));
		
		cq.where(cb.and(
				cb.equal(root.get(DepartmentEntity_.departmentCode), code),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		try {
			return Optional.ofNullable(em.createQuery(cq).getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<DepartmentDomainDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByDesc parameters : {}, {}, {}", locale, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<DepartmentDomainDTO> cq = cb.createQuery(DepartmentDomainDTO.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.select(cb.construct(		DepartmentDomainDTO.class, 
							   		root.get(DepartmentEntity_.departmentId),
							   		root.get(DepartmentEntity_.departmentCode),
							   		attribute.get(DepartmentAttribute_.departmentDesc),
							   		attribute.get(DepartmentAttribute_.lclCd)
		));
		
		cq.where(cb.and(
				cb.equal(attribute.get(DepartmentAttribute_.departmentDesc), desc),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		try {
			return Optional.ofNullable(em.createQuery(cq).getSingleResult());
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
}