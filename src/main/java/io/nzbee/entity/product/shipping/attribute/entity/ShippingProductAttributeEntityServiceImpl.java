package io.nzbee.entity.product.shipping.attribute.entity;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingProductAttributeEntityServiceImpl implements IShippingProductAttributeEntityService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingProductAttributeEntityRepository productAttributeRepository; 
	
	@Override
	public Optional<ShippingProductAttributeEntity> findByCode(String locale, String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByProductCode with parameter {} ,{}", locale, code);
		return productAttributeRepository.findByLclCdAndProductShippingProductProductUPC(locale, code);
	}
	
	@Override
	public void save(ShippingProductAttributeEntity t) {
		productAttributeRepository.save(t);
	}

	@Override
	public void update(ShippingProductAttributeEntity t) {
		productAttributeRepository.save(t);
	}

	@Override
	public void delete(ShippingProductAttributeEntity t) {
		productAttributeRepository.delete(t);		
	}

}
