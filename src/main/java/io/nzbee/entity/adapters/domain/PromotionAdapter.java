package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import io.nzbee.entity.promotion.IPromotionDTOService;

@Component
public class PromotionAdapter implements IPromotionPortService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionTypeService promotionTypeService;
	
	@Autowired
	private IPromotionDTOService promotionService;
	
	@Autowired
	private IPromotionMapper promotionMapper;

	@Override
	public Bag applyAll(Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".applyAll with parameter {}", bag.getCustomer().getUserName());
		
		
		bag.getCoupons().stream()
		.forEach(c -> {
				LOGGER.debug("counpon is present: " + c);
				//we want to fist find the promotion type by coupon code
				Optional<PromotionTypeEntity> opt = promotionTypeService.findByTriggerCode(c);
				
				if(opt.isPresent()) {
					LOGGER.debug("promotion type found: " + opt.get().getPromotionTypeDesc());
					
					//bag level logic
					if(opt.get().getPromotionTypeCode().equals(Constants.promotionTypeBag)) {
						Promotion p = promotionMapper.DTOToDo(promotionService.findBagPromotion(c).get());
						LOGGER.debug("found bag promotion: " + p.getPromotionCode());
						p.compute();
						return;
					}
					
					
					//item level logic
					//for each item in the bag compute the promotion
					//Promotion p =
					if(opt.get().getPromotionTypeCode().equals(Constants.promotionTypeProduct)) {
						Optional<Promotion> p = 
							bag.getBagItems().stream()
							.filter(e -> promotionService.findProductPromotion(e.getBagItem().getProductUPC(), c).isPresent())
							.map(i ->  promotionMapper.DTOToDo(promotionService.findProductPromotion(i.getBagItem().getProductUPC(), c).get()))
							.findAny();
						
						if(p.isPresent()) {
							LOGGER.debug("found product promotion: " + p.get().getPromotionCode());
							p.get().compute();
						}
					}
				}
		});
		
		//LOGGER.debug("no coupon found");
		return bag;	
	}


	

}
