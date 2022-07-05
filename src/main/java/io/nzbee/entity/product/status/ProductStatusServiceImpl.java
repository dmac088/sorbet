package io.nzbee.entity.product.status;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductStatusServiceImpl implements IProductStatusService {

	@Autowired 
	private IProductStatusRepository productStatusRepository;
	
	@Override
	public Optional<ProductStatusEntity> findByProductStatusCode(String code) {
		return productStatusRepository.findByProductStatusCode(code);
	}

	@Override
	public List<ProductStatusEntity> findAll() {
		return productStatusRepository.findAll();
	}
}
