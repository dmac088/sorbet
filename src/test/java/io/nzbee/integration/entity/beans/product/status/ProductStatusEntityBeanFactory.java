package io.nzbee.integration.entity.beans.product.status;


import org.springframework.stereotype.Service;
import io.nzbee.entity.product.status.ProductStatusEntity;

@Service

public class ProductStatusEntityBeanFactory implements IProductStatusEntityBeanFactory {

	@Override
	public final ProductStatusEntity getBean() {
		ProductStatusEntity productStatus = new ProductStatusEntity();
		productStatus.setCode("TST01");
		productStatus.setDesc("test product status");
		return productStatus;
	}
	
}
