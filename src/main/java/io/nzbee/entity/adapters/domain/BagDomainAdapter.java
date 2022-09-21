package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.promotion.bag.PromotionBag;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.entity.bag.domain.BagDomainDTO;
import io.nzbee.entity.bag.domain.IBagDomainDTOMapper;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.domain.promotion.IPromotionBagDomainDTOMapper;
import io.nzbee.entity.bag.domain.promotion.IPromotionBagDomainDTOService;
import io.nzbee.entity.bag.domain.promotion.PromotionBagDomainDTO;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class BagDomainAdapter implements IBagPortService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagDomainDTOService bagService;
	
	@Autowired
	private IPromotionBagDomainDTOService promotionBagService;
	
	@Autowired
	private IPromotionBagDomainDTOMapper promotionBagMapper;
	
	@Autowired
	private IBagDomainDTOMapper bagMapper;
	
	
	@Override
	public Bag findByCode(Locale locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale.getLocale(), locale.getCurrency().getCurrencyCode(), userName);
		
		Optional<BagDomainDTO> ob = bagService.findByCode(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), userName);

		//if there is no current bag, get a new one
		BagDomainDTO b = ob.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.customerNotFound, locale, userName));
	
		//map the bag to a domain object
		return bagMapper.toDo(b);
	}
	
	@Override
	@Transactional
	public void save(Bag domainObject) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".save()");
		
		BagEntity b = bagMapper.toEntity(domainObject);
		
		bagService.save(b);
	}
	
	@Override
	public PromotionBag findByCode(Locale locale, UserName userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getPromotionItems() with parameter {}, {}, {}", locale, userName);
		PromotionBagDomainDTO pb = promotionBagService.findByCode(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), userName.toString())
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.bagNotFound, locale, userName.toString()));
		return promotionBagMapper.toDo(pb);
	}

}
