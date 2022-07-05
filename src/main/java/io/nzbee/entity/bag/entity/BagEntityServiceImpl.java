package io.nzbee.entity.bag.entity;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagEntityServiceImpl implements IBagEntityService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagEntityRepository bagRepository;
	
	@Override
	public Optional<BagEntity> findById(Long id) {
		return bagRepository.findById(id);
	}

	@Override
	public Optional<BagEntity> findByCode(String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode() with parameter {}", userName);
		return bagRepository.findByPartyPartyUserUsername(userName);
	}


	@Override
	public void save(BagEntity t) {
		bagRepository.save(t);
	}

	@Override
	public void update(BagEntity t) {
		bagRepository.save(t);
	}

	@Override
	public void delete(BagEntity t) {
		bagRepository.delete(t);
	}

}
