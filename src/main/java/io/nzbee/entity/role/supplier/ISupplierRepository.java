package io.nzbee.entity.role.supplier;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long>  {

	List<SupplierEntity> findAll();

	Optional<SupplierEntity> findBySupplierNumber(String supplierNumber);

}
