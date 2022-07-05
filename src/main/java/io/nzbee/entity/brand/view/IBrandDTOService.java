package io.nzbee.entity.brand.view;

import java.util.Collection;
import io.nzbee.entity.IEntityService;

public interface IBrandDTOService extends IEntityService<BrandDTO> {
	
	Collection<BrandDTO> findByAllShippingProviders(String locale);

}
