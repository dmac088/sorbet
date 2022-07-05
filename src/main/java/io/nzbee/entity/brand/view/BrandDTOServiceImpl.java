package io.nzbee.entity.brand.view;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="brandViewService")
public class BrandDTOServiceImpl implements IBrandDTOService {

	@Autowired
	private IBrandDTORepository brandRepo;
	
	@Override
	public void save(BrandDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandDTO t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Collection<BrandDTO> findByAllShippingProviders(String locale) {
		return brandRepo.findByAllShippingProviders(locale) ;
	}
	
}
