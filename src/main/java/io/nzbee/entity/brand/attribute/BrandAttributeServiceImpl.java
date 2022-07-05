package io.nzbee.entity.brand.attribute;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class BrandAttributeServiceImpl implements IBrandAttributeService {

	@Autowired
	private IBrandAttributeRepository brandAttributeRepository;

	@Override
	public void save(BrandAttributeEntity t) {
		brandAttributeRepository.save(t);
	}

	@Override
	public void update(BrandAttributeEntity t, String[] params) {
		brandAttributeRepository.save(t);
	}

	@Override
	public void delete(BrandAttributeEntity t) {
		brandAttributeRepository.delete(t);		
	}

	@Override
	public Optional<BrandAttributeEntity> getBrandAttribute(Long id, String locale) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(locale, id);
	}
	
	@Override
	public Optional<BrandAttributeEntity> getBrandAttributeEN(Long id) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<BrandAttributeEntity> getBrandAttributeHK(Long id) {
		return brandAttributeRepository.findByLclCdAndBrandBrandId(Constants.localeZHHK, id);
	}

	@Override
	public Optional<BrandAttributeEntity> findById(long id) {
		return brandAttributeRepository.findById(id);
	}

	
}
