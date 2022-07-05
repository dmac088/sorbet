package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.entity.bag.domain.BagDomainDTO;
import io.nzbee.entity.bag.domain.IBagDomainDTOMapper;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class BagDomainAdapter implements IBagPortService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagDomainDTOService bagService;
	
	@Autowired
	private IBagDomainDTOMapper bagMapper;
	
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale, currency, userName);
		
		Optional<BagDomainDTO> ob = bagService.findByCode(currency, userName);

		//if there is no current bag, get a new one
		BagDomainDTO b = ob.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.customerNotFound, locale, userName));
	
		//map the bag to a domain object
		return bagMapper.DTOToDo(b);
	}

	@Override
	public void save(Bag domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		BagEntity b = bagMapper.doToEntity(domainObject);
		bagService.save(b);
	}

}
