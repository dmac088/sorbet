package io.nzbee.entity.bag.view;

import java.math.BigDecimal;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BagViewDTOServiceImpl implements IBagViewDTOService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagViewDTODao bagDao;
	
	@Autowired
	private IBagViewDTORepository bagRepository;
	
	@Override
	public Optional<BagViewDTO> findByCode(String locale, String currency, String rootCategory, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}, {}", locale, currency, rootCategory, userName);
		return bagDao.findByCode(locale, currency, rootCategory, userName);
	}

	@Override
	public BigDecimal getBagWeight(String userName) {
		return bagRepository.getBagWeight(userName);
	}

}
