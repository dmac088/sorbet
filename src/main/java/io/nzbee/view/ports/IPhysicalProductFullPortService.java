package io.nzbee.view.ports;

import java.util.Optional;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public interface IPhysicalProductFullPortService extends IViewPortService<PhysicalProductFullView> {
 

	RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullModel> findAll(String locale, String currency,
			String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes, Double maxPrice, String page, String size, String sort);

	Optional<PhysicalProductFullView> findByCode(String locale, String currency, String code);
	
	
}
