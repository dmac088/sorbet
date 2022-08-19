package io.nzbee.entity.bag.item.type;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagItemTypeServiceImpl implements IBagItemTypeService {

	@Autowired
	private IBagItemTypeRepository bagItemTypeRepository;
	
	@Override
	public Optional<BagItemTypeEntity> findById(Long id) {
		return bagItemTypeRepository.findById(id);
	}
	
	@Override
	public Optional<BagItemTypeEntity> findByCode(String code) {
		return bagItemTypeRepository.findByBagItemTypeCode(code);
	}
	
	@Override
	public void save(BagItemTypeEntity t) {
		bagItemTypeRepository.save(t);
		
	}

	@Override
	public void update(BagItemTypeEntity t) {
		bagItemTypeRepository.save(t);
	}

	@Override
	public void delete(BagItemTypeEntity t) {
		bagItemTypeRepository.delete(t);
	}


	
	
}
