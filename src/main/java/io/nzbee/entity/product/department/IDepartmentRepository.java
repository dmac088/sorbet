package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
	
	List<DepartmentEntity> findAll();
	
	Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);
}

