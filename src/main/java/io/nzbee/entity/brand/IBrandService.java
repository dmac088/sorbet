package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IEntityService;

public interface IBrandService extends IEntityService<BrandEntity> {

	Optional<BrandEntity> findByCode(String brandCode);
	
	List<BrandEntity> findAll();
}
