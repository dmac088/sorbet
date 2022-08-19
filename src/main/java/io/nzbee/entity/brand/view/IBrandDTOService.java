package io.nzbee.entity.brand.view;

import java.util.Collection;

public interface IBrandDTOService {
	
	Collection<BrandDTO> findByAllShippingProviders(String locale);

}
