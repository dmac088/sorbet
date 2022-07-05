package io.nzbee.entity.product.physical.view;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductDTODao {

	Optional<PhysicalProductDTO> findById(String locale, String currency, Long productId);
	
	Optional<PhysicalProductDTO> findByCode(String locale, String currency, String code);

	Optional<PhysicalProductDTO> findByDesc(String locale, String currency, String desc);
	
	List<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory,StringCollectionWrapper codes);
	
	Page<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory, Pageable pageable,
			String orderby);
	
	Page<PhysicalProductDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort);

}
