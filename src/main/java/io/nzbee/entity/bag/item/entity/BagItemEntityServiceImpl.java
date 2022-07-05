package io.nzbee.entity.bag.item.entity;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagItemEntityServiceImpl implements IBagItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagItemRepository bagItemRepository;

	@Override
	public Optional<BagItemEntity> findById(Long id) {
		return bagItemRepository.findById(id);
	}

	@Override
	public void save(BagItemEntity t) {
		bagItemRepository.save(t);
	}

	@Override
	public void update(BagItemEntity t) {
		bagItemRepository.save(t);
	}

	@Override
	public void delete(BagItemEntity t) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".delete with parameters {}", t.getBagItemId());
		bagItemRepository.delete(t);
	}


}
