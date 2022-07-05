package io.nzbee.entity.product.status;

import java.util.List;
import java.util.Optional;

public interface IProductStatusService {

	Optional<ProductStatusEntity> findByProductStatusCode(String code); 
	
	List<ProductStatusEntity> findAll();
	
}
