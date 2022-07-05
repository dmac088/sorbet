package io.nzbee.entity.adapters.view;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullDTOService;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullViewMapper;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public class PhysicalProductFullAdapterImpl implements IPhysicalProductFullPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPhysicalProductFullDTOService physicalProductFullDTOService;
	
	@Autowired
	private IPhysicalProductFullViewMapper productMapper;
	
	@Override
	public RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullModel> findAll(
			String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, String page,
			String size, String sort) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll() ");
		return null;
	}

	@Override
	public Optional<PhysicalProductFullView> findByCode(String locale, String currency, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findAll with parameters {}, {}, {}", locale, currency, code);
		return Optional.ofNullable(productMapper.toView(physicalProductFullDTOService.findByCode(locale, currency, code).get()));
	}

}
