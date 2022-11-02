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
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById() with parameter {}", id);
		return bagRepository.findById(id);
	}

	@Override
	public Optional<BagEntity> findByCode(String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode() with parameter {}", userName);
		return bagRepository.findByPartyPartyUserUsername(userName);
	}
	
	@Override
	public void save(BagEntity t) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save() with parameter {}", t.getParty().getUser().getUsername());
		bagRepository.save(t);
	}

}
