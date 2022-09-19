package io.nzbee.entity.adapters.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.promotion.item.PromotionItem;
import io.nzbee.entity.bag.domain.BagDomainDTO;
import io.nzbee.entity.bag.domain.IBagDomainDTOMapper;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.item.domain.promotion.IPromotionBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.promotion.IPromotionBagItemDomainDTOMapper;
import io.nzbee.exceptions.EntityNotFoundException;

@Service
public class BagDomainAdapter implements IBagPortService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagDomainDTOService bagService;
	
	@Autowired
	private IPromotionBagItemDomainDTOService promotionBagItemService;
	
	@Autowired
	private IPromotionBagItemDomainDTOMapper promotionBagItemMapper;
	
	@Autowired
	private IBagDomainDTOMapper bagMapper;
	
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale, currency, userName);
		
		Optional<BagDomainDTO> ob = bagService.findByCode(currency, userName);

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
	public List<PromotionItem> getPromotionItems(String locale, String currency, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getPromotionItems() with parameter {}, {}, {}", locale, currency, userName);
		return promotionBagItemService.getItems(currency, currency, userName)
			.stream().map(i -> promotionBagItemMapper.toDo(i)).collect(Collectors.toList());
	}

}
