package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

	Optional<DepartmentEntity> findByCode(String code);

	Optional<DepartmentEntity> findById(Long id);
	
	List<DepartmentEntity> findAll();

	Optional<DepartmentDomainDTO> findByCode(String locale, String code);

	void save(DepartmentEntity department);

}
