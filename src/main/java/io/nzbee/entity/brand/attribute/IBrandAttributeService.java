package io.nzbee.entity.brand.attribute;

import java.util.Optional;

public interface IBrandAttributeService {
	
	Optional<BrandAttributeEntity> getBrandAttributeEN(Long id);
	
	Optional<BrandAttributeEntity> getBrandAttributeHK(Long id);

	Optional<BrandAttributeEntity> getBrandAttribute(Long id, String locale);

	Optional<BrandAttributeEntity> findById(long id);

	void save(BrandAttributeEntity t);

	void update(BrandAttributeEntity t, String[] params);

	void delete(BrandAttributeEntity t);


}
