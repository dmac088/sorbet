package io.nzbee.entity.product.department;

import java.util.Optional;

public interface IDepartmentDao {

	Optional<DepartmentEntity> findById(long id);

	Optional<DepartmentDomainDTO> findById(String locale, Long id);

	Optional<DepartmentDomainDTO> findByCode(String locale, String code);

	Optional<DepartmentDomainDTO> findByDesc(String locale, String desc);

}
