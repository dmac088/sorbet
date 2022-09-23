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
		LOGGER.debug("call " + getClass().getSimpleName() + ".findById() with parameters: {}", id);
		return bagItemRepository.findById(id);
	}

}
