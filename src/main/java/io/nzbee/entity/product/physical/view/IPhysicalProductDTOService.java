package io.nzbee.entity.product.physical.view;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductDTOService {

	Optional<PhysicalProductDTO> findById(String locale, String currency, Long productId);
	
	Optional<PhysicalProductDTO> findByCode(String locale, String currency, String code);

	Optional<PhysicalProductDTO> findByDesc(String locale, String currency, String desc);
	
	List<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes);

}
