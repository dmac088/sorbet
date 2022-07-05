package io.nzbee.entity.role.supplier;

import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface ISupplierService extends IEntityService<SupplierEntity> {

	Optional<SupplierEntity> findByCode(String code);

	Optional<SupplierEntity> findById(Long id);

}
